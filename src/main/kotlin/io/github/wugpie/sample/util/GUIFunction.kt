package io.github.wugpie.sample.util

import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

fun getItem(item : Material, amount : Int, name : String, nbthide : Boolean, vararg lores : String) : ItemStack{
    var result = ItemStack(item, amount, 0)
    var meta = result.itemMeta

    meta.apply {
        lore = lores.toList()
        setDisplayName(name)
    }

    result.setItemMeta(meta)

    if(nbthide) result.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)


    return result
}