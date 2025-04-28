package io.github.wugpie.sample.util

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File


fun Player.getFile() : File {
    return File("plugins" + File.separator
            + "example" + File.separator
            + "${this.uniqueId}.yml")
}

fun Player.setValue(name : String, value : Int) {
    val stat = YamlConfiguration.loadConfiguration(this.getFile())
    stat.set(name, value)
    stat.save(this.getFile())
}

fun Player.getValue(name : String) : Int {
    return YamlConfiguration.loadConfiguration(this.getFile()).getInt(name)
}

fun Player.resetFile() {
    this.apply {
       //초기 상태 입력
    }
}

