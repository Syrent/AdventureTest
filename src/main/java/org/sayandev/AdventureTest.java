package org.sayandev;

import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

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
        if (event.getMessage().equals("book")) {
            getLogger().warning("opening book");
            bukkitAudiences.player(event.getPlayer()).openBook(Book.builder()
                    .author(Component.text("Kyori", NamedTextColor.AQUA))
                    .addPage(Component.text("is amazing", NamedTextColor.DARK_GRAY))
                    .title(Component.text("Adventure", NamedTextColor.LIGHT_PURPLE))
                    .build());
        }
    }
}