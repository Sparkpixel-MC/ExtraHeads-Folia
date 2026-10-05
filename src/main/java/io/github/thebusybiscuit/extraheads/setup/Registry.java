package io.github.thebusybiscuit.extraheads.setup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;

import org.bukkit.entity.EntityType;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;

import lombok.Getter;

@Getter
public class Registry {

    private final Config config;
    // HeadListener 在各 Region 线程读取，ItemSetup 在启用时写入，需线程安全
    private final Map<EntityType, SlimefunItem> heads = new ConcurrentHashMap<>();
    private final Map<String, Map<EntityType, String>> entityNames = new ConcurrentHashMap<>();

    public Registry(@Nonnull Config config) {
        this.config = config;
    }
}
