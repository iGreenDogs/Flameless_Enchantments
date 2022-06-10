package com.bobwazheer.plugins.firelessenchants;

import com.bobwazheer.plugins.firelessenchants.customenchants.GlowEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public final class FirelessEnchants extends JavaPlugin {


    private static FirelessEnchants plugin;
    public static GlowEnchantment glowEnchantment;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        glowEnchantment = new GlowEnchantment("glow");
        registerEnchantment(glowEnchantment);
        this.getServer().getPluginManager().registerEvents(glowEnchantment, this);
    }

    @Override
    public void onDisable() {
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            if(byKey.containsKey(glowEnchantment.getKey())) {
                byKey.remove(glowEnchantment.getKey());
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            if(byName.containsKey(glowEnchantment.getName())) {
                byName.remove(glowEnchantment.getName());
            }
        } catch (Exception ignored) { }
    }

    public static FirelessEnchants getPlugin(){
        return plugin;
    }

    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");

        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if (registered){

        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("flameglow")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                ItemStack item = p.getInventory().getItemInMainHand();
                item.addEnchantment(FirelessEnchants.glowEnchantment, 1);
                ItemMeta meta = item.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "Glow");
                meta.setLore(lore);
                item.setItemMeta(meta);
                p.getInventory().setItemInMainHand(item);

            }
        } else {
            sender.sendMessage("That enchant does not exist/you entered the command wrong");
        }

        return true;
    }
}
