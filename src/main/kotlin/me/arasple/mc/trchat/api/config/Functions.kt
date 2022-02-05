package me.arasple.mc.trchat.api.config

import me.arasple.mc.trchat.module.conf.Property
import me.arasple.mc.trchat.module.internal.script.Condition
import me.arasple.mc.trchat.util.toCondition
import org.bukkit.Bukkit
import taboolib.common5.Baffle
import taboolib.library.configuration.ConfigurationSection
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigNode
import taboolib.module.configuration.ConfigNodeTransfer
import taboolib.module.configuration.Configuration
import java.util.concurrent.TimeUnit

/**
 * @author wlys
 * @since 2021/12/12 11:40
 */
object Functions {

    @Config("function.yml", autoReload = true)
    lateinit var CONF: Configuration
        private set

    @ConfigNode("General.Command-Controller.List", "function.yml")
    val commandDelay = ConfigNodeTransfer<List<*>, Map<Regex, Baffle>> {
        mapNotNull { string ->
            val (cmd, property) = Property.from(string!!.toString())
            val cooldown = property[Property.COOLDOWN]?.toFloat() ?: return@mapNotNull null
            val mCmd = Bukkit.getCommandAliases().entries.firstOrNull { (_, value) ->
                value.any { it.equals(cmd.split(" ")[0], ignoreCase = true) }
            }
            val key = if (mCmd != null) mCmd.key else cmd
            val regex = if (property[Property.EXACT].toBoolean()) {
                Regex("(?i)$key\$")
            } else {
                Regex("(?i)$key.*")
            }
            regex to Baffle.of((cooldown * 1000).toLong(), TimeUnit.MILLISECONDS)
        }.toMap()
    }

    @ConfigNode("General.Command-Controller.List", "function.yml")
    val commandCondition = ConfigNodeTransfer<List<*>, Map<Regex, Condition>> {
        mapNotNull { string ->
            val (cmd, property) = Property.from(string!!.toString())
            val condition = property[Property.CONDITION]?.toCondition() ?: return@mapNotNull null
            val mCmd = Bukkit.getCommandAliases().entries.firstOrNull { (_, value) ->
                value.any { it.equals(cmd.split(" ")[0], ignoreCase = true) }
            }
            val key = if (mCmd != null) mCmd.key + cmd.removePrefix(mCmd.key) else cmd
            val regex = if (property[Property.EXACT].toBoolean()) {
                Regex("(?i)$key\$")
            } else {
                Regex("(?i)$key.*")
            }
            regex to condition
        }.toMap()
    }

    @ConfigNode("General.Item-Show.Cooldowns", "function.yml")
    val itemShowDelay = ConfigNodeTransfer<Double, Baffle> { Baffle.of((this * 1000).toLong(), TimeUnit.MILLISECONDS) }

    @ConfigNode("General.Item-Show.Keys", "function.yml")
    val itemShowKeys = ConfigNodeTransfer<List<String>, List<Regex>> { map { Regex("$it(-[1-9])?") } }

    @ConfigNode("General.Mention.Cooldowns", "function.yml")
    val mentionDelay = ConfigNodeTransfer<Double, Baffle> { Baffle.of((this * 1000).toLong(), TimeUnit.MILLISECONDS) }

    @ConfigNode("General.Item-Show", "function.yml")
    lateinit var itemShow: ConfigurationSection

    @ConfigNode("General.Mention", "function.yml")
    lateinit var mention: ConfigurationSection

    @ConfigNode("General.Inventory-Show", "function.yml")
    lateinit var inventoryShow: ConfigurationSection
}