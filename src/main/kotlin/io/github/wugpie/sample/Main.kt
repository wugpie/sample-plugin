package io.github.wugpie.sample

import io.github.wugpie.sample.command.CommandExample
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import org.bukkit.ChatColor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.io.File


class Main : JavaPlugin(), Listener {


    override fun onEnable() {
        server.consoleSender.sendMessage("${ChatColor.RED}플러그인이 활성화 되었습니다")
        server.pluginManager.registerEvents(this, this)

        //초기 폴더 생성
        val folder = File(server.pluginsFolder, "example")
        if(!folder.exists()) folder.mkdir()

        //brigadier 명령어 등록
        val manager = this.lifecycleManager
        manager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
            val dispatcher = event.registrar().dispatcher
            if (event.registrar().dispatcher != null) {
                dispatcher.register(CommandExample.build())
                logger.info("Brigadier command registered successfully.")
            } else {
                logger.severe("Failed to register Brigadier command.")
            }
        }
    }


    override fun onDisable() {
        server.consoleSender.sendMessage("${ChatColor.RED}플러그인이 비활성화 되었습니다!")
    }
}
