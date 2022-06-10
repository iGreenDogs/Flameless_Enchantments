package com.bobwazheer.plugins.firelessenchants.customenchants;

import com.bobwazheer.plugins.firelessenchants.FirelessEnchants;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class GlowEnchantment extends Enchantment implements Listener {


    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();

            if (player.getEquipment().getItemInMainHand().getEnchantments().containsKey(Enchantment.getByKey(FirelessEnchants.glowEnchantment.getKey()))) {
                PotionEffect effect = new PotionEffect(PotionEffectType.GLOWING, 1200, 1);
                effect.apply((LivingEntity) e.getEntity());
            }
        }
    }

    public GlowEnchantment(String namespace) {
        super(new NamespacedKey(FirelessEnchants.getPlugin(), namespace));
    }

    @Override
    public String getName() {
        return "Glow";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }
}
