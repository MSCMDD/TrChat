# TrChat Update Logs #

#### VERSION 1.5
  - ##### 1.51
    - 修复了部分情况下 PlaceholderAPI 的报错
    - 修复了配置文件控制颜色代码开关无效的问题
    - 新增了强制默认聊天颜色的权限节点 trchat.color.force-defaultcolor
    - 屏蔽了 bStats 的一个报错

#### Old logs
	Version 1.5:
		Date: 2019.10.19    
		Updates:
		 - 改进部分代码
		 - 修复 "functions" 命名错误
		 - 修复了一些报错
		 - 正式改名为 TrChat, 更新介绍图

	Version 1.4:
		Date: 2019.10.12
		Updates:
		 - [!] 该版本重制配置文件内容较多, 请备份+删除 TrChat文件夹 后更新
		 - >>
		 - 配置文件重写, 分为 settings.yml / formats.yml / filter.yml / functions.yml
		 - 云端敏感词库上线, 自动更新敏感词、支持自定义白名单词 (正在持续增加中)
		 - 每个聊天频道均支持多个自定义聊天格式，优先级权限筛选
		 - 添加了一个选项，可以自动下载并载入指定PAPI变量拓展
		 - 修复了1.12展示物品报错
		 - 修复了旧版本的各种错误、漏洞
		 - 移除了 PerWorldChat 功能

	Version 1.3X:
		Date: 2019.8.30 - 2019.9.13
		Updates:
  		 1.31:
  		 - 新增 @At 在线玩家功能 (音效+TITLE提示/忽略大小写式判断/自定义At文字高亮)
  		 1.32:
  		 - 修复消息默认彩色代码配置问题
  		 - 现在颜色代码也可以在编辑书中使用(需要指定权限)
  		 1.33:
  		 - 修复玩家数据报错
  		 - 修复API事件不被call
  		 - 自动更新配置文件选项
  		 - 聊天内容的发包将改为 CHAT 类型
  		 - 依赖更至 Taboolib v5.04
  		 1.34:
  		 - 修复 <> 中内容消失的问题
  		 - 禁止在私聊中At玩家
  		 - 新增At玩家的冷却 (常规/单个玩家冷却)
  		 - 缓存展示物品，提高性能
  		 1.35:
  		 - 修复了数据读写的问题
  		 - 修复了v1.34新增的BUG
  		 - 重写了更新检测器
  		 - 移除了控制面板
  		 - 新增聊天内容字符长度的限制
  		 1.36:
  		 - 增加了更多 bStats 自定义统计项
  		 - 修复了私聊中过滤器绕过权限无效的问题
  		 - 改进了过滤器
  		 1.37:
  		 - 修复了 GlobalShoutEvent & PrivateMessageEvent
  		 - 修复了 私聊报错问题
  		 - 现在可以通过 "[展示物品变量]-[槽位]" 来展示背包指定位置的物品
  		 - 👆 例如 %i-0 ~ %i-9
  		 1.38:
  		 - 修复了1.12版无法全局喊话问题
  		 1.39:
  		 - 修复了At功能

	Version 1.3:
		Date: 2019.8.18
		Updates:
		 - 依赖更新至 Taboolib v5.03
		 - 改进了基于权限的彩聊代码处理
		 - 改进了插件部分服务注入方式
		 - 改进了控制面板GUI跨版本物品兼容
		 - 改进了私聊频道代码
		 - 改进了物品展示处理方式
		 - 修复了自定义物品展示代码失效
		 - 一条消息现在支持同时展示多个物品
		 - 改由 Taboolib 存储数据
		 - 新增命令 /spy, 在游戏内切换是否监听玩家私聊
		 - 现在颜色代码的权限支持木牌/铁砧使用彩色代码！
		 - 新增多个配置节点决定是否启用颜色代码功能
		 - 炫酷的载入文字画LOGO (可修改/关闭)
		 - bStats 更换为 MetricsLite 并优化, 减小了插件体积
		 - 提供了牛逼的API (TrChatAPI) 方便自定义拓展
		 - LChatPrivateMessageEvent - API 私聊事件
		 - LChatShoutEvent - API 私聊事件
		 - 配置文件大改, 新增配置版号 (自动备份更新)

	Version 1.2:
		Date: 2019.8.17 20:30
		Updates:
		 - 支持 CatServer
		 - 全局喊话将强制要求Bungee启用, 否则则提示玩家
		 - 修复了聊天冷却发送时事件未取消的问题
		 - 更新通知新增 Mcbbs 地址

	Version 1.1:
		Date: 2019.8.17 13:30
		Updates:
		 - 优化代码
		 - 自动读取 spigot.yml 配置判断是否启用 Bungee 支持
		 - 管理频道支持单独 Spigot 使用
		 - 现在即使未在管理频道, 也可通过/staff [MESSAGE]发送管频消息
		 - 改进BungeeCord的监听器

	Version 1.0:
		Date: 2019.8.16
		Updates:
		 - 正式版