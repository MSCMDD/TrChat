package me.arasple.mc.trchat.formats.part;

import io.izzel.taboolib.internal.apache.lang3.math.NumberUtils;
import io.izzel.taboolib.module.locale.TLocale;
import io.izzel.taboolib.module.tellraw.TellrawJson;
import io.izzel.taboolib.util.Strings;
import io.izzel.taboolib.util.Variables;
import io.izzel.taboolib.util.item.Items;
import me.arasple.mc.trchat.TrChatFiles;
import me.arasple.mc.trchat.data.Cooldowns;
import me.arasple.mc.trchat.data.DataHandler;
import me.arasple.mc.trchat.utils.MessageColors;
import me.arasple.mc.trchat.utils.Players;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * @author Arasple
 * @date 2019/10/12 20:23
 */
public class MessagePart extends JsonPart {

    private ChatColor defaultColor;

    public MessagePart(Map<String, String> map) {
        super(map);
        this.defaultColor = ChatColor.getByChar(map.getOrDefault("default-color", "7").charAt(0));
    }

    public TellrawJson toTellrawJson(Player player, String message, boolean... values) {
        if (TrChatFiles.getSettings().getBoolean("CHAT-COLOR.CHAT")) {
            message = MessageColors.replaceWithPermission(player, message);
        }

        ChatColor defaultMsgColor = MessageColors.catchDefaultMessageColor(player, defaultColor);

        boolean mentionEnable = DataHandler.getCooldownLeft(player.getUniqueId(), Cooldowns.CooldownType.MENTION) <= 0 && values.length > 0 && values[0];
        String mentionFormat = TrChatFiles.getFunctions().getStringColored("MENTION.FORMAT");

        if (mentionEnable) {
            // At玩家的格式
            for (Player o : Bukkit.getOnlinePlayers()) {
                String playerName = o.getName();
                message = message.replaceAll("(?i)@" + playerName, "<AT:" + playerName + ">");
            }
        }

        // 取得配置信息
        boolean itemShowEnable = TrChatFiles.getFunctions().getBoolean("ITEM-SHOW.ENABLE", true);
        List<String> itemKeys = TrChatFiles.getFunctions().getStringList("ITEM-SHOW.KEYS");
        String itemFormat = TrChatFiles.getFunctions().getStringColored("ITEM-SHOW.FORMAT", "§8[§3{0} §bx{1}§8]");
        // 替换物品变量
        if (itemShowEnable) {
            for (String key : itemKeys) {
                for (int i = 0; i < 9; i++) {
                    message = message.replace(key + "-" + i, "<ITEM:" + i + ">");
                }
                message = message.replace(key, "<ITEM:" + player.getInventory().getHeldItemSlot() + ">");
            }
        }

        TellrawJson format = TellrawJson.create();

        for (Variables.Variable variable : new Variables(message).find().getVariableList()) {
            String var = variable.getText();

            if (itemShowEnable && var.startsWith("ITEM")) {
                int slot = NumberUtils.toInt(var.split(":")[1], player.getInventory().getHeldItemSlot());
                ItemStack item = player.getInventory().getItem(slot) != null ? player.getInventory().getItem(slot) : new ItemStack(Material.AIR);
                format.append(DataHandler.getItemshowCache().computeIfAbsent(item, i -> TellrawJson.create().append(Strings.replaceWithOrder(itemFormat, Items.isNull(item) ? "空气" : Items.getName(item), item.getType() != Material.AIR ? item.getAmount() : 1) + defaultMsgColor).hoverItem(item)));
            } else if (mentionEnable && var.startsWith("AT:")) {
                String atPlayer = var.substring(3);
                if (Players.isPlayerOnline(atPlayer)) {
                    format.append(Strings.replaceWithOrder(mentionFormat, atPlayer) + defaultMsgColor);
                    if (TrChatFiles.getFunctions().getBoolean("MENTION.NOTIFY")) {
                        TLocale.sendTo(Bukkit.getPlayer(atPlayer), "MENTIONS.NOTIFY", player.getName());
                    }
                }
                DataHandler.updateCooldown(player.getUniqueId(), Cooldowns.CooldownType.MENTION, TrChatFiles.getFunctions().getLong("MENTION.COOLDOWNS"));
            } else {
                format.append(applyJson(player, TellrawJson.create().append(defaultMsgColor + Strings.replaceWithOrder("{0}" + variable.getText() + "{1}", variable.isVariable() ? "<" : "", variable.isVariable() ? ">" : ""))));
            }
        }

        return format;
    }

    public ChatColor getDefaultColor() {
        return defaultColor;
    }

}