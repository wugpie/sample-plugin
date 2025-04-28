package io.github.wugpie.sample.command

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.exceptions.CommandSyntaxException
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands.argument
import org.bukkit.entity.Player

object CommandExample {
    fun build() : LiteralArgumentBuilder<CommandSourceStack>{
        return LiteralArgumentBuilder.literal<CommandSourceStack>("example")
            .then(
                argument("name", StringArgumentType.string())
                    .suggests { context, builder ->
                        listOf("0", "1", "2", "3", "4", "5")
                            .filter { it.startsWith(builder.remaining) }
                            .forEach { builder.suggest(it) }
                        builder.buildFuture()
                    }
                    .then(
                        argument("value", IntegerArgumentType.integer(0, 450))
                            .requires { it.sender is Player }
                            .executes{ context ->
                                val player = context.source.executor as Player
                                val name = StringArgumentType.getString(context, "name")
                                val validStats = listOf("0", "1", "2", "3", "4", "5")
                                if (name !in validStats) {
                                    val reader = StringReader(context.input).apply {
                                        cursor = context.range.start
                                    }
                                    throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.literalIncorrect()
                                        .createWithContext(reader, name)
                                }

                                val nameValue = name
                                val value = IntegerArgumentType.getInteger(context, "value")
                                player.sendMessage(nameValue)
                                player.sendMessage(value.toString())
                                1
                            }
                    )
            )
    }
}