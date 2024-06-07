package org.sayandev;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.sayandev.stickynote.lib.kyori.adventure.inventory.Book;
import org.sayandev.stickynote.lib.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.sayandev.stickynote.lib.kyori.adventure.text.Component;
import org.sayandev.stickynote.lib.kyori.adventure.text.event.ClickEvent;
import org.sayandev.stickynote.lib.kyori.adventure.text.event.HoverEvent;
import org.sayandev.stickynote.lib.kyori.adventure.text.format.NamedTextColor;
import org.sayandev.stickynote.lib.kyori.adventure.text.minimessage.MiniMessage;

public class AdventureTest extends JavaPlugin implements Listener {

    BukkitAudiences bukkitAudiences;

    @Override
    public void onEnable() {
        bukkitAudiences = BukkitAudiences.create(this);
        getLogger().warning("class: " + bukkitAudiences.getClass());
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        getLogger().info(event.getMessage());
        bukkitAudiences.player(event.getPlayer()).sendActionBar(Component.text(event.getMessage()));
        bukkitAudiences.player(event.getPlayer()).sendMessage(Component.text("ur message: " + event.getMessage(), NamedTextColor.RED)
                .clickEvent(ClickEvent.suggestCommand("/version TestProject"))
                .hoverEvent(HoverEvent.showText(MiniMessage.miniMessage().deserialize("<red>This is red <rainbow>and this is very rainbow!!!"))));
        bukkitAudiences.player(event.getPlayer()).sendActionBar(Component.text(event.getMessage(), NamedTextColor.GOLD));
        if (event.getMessage().equals("book")) {
            bukkitAudiences.player(event.getPlayer()).openBook(Book.builder()
                    .author(Component.text("Kyori", NamedTextColor.AQUA))
                    .addPage(Component.text("is amazing", NamedTextColor.DARK_GRAY))
                    .title(Component.text("Adventure", NamedTextColor.LIGHT_PURPLE))
                    .build());
        }
    }
}