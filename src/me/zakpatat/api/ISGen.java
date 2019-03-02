package me.zakpatat.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ISGen {

	private ItemStack i;

	public ISGen(Material m) {
		i = new ItemStack(m);
	}

	public ISGen(Material m, int c) {
		i = new ItemStack(m, c);
	}
	
	public ISGen(Material m, int c, short b) {
		i = new ItemStack(m, c, b);
	}
	
	public ItemStack getItem() {
		return i;
	}
	
	public ItemMeta getItemMeta() {
		return i.getItemMeta();
	}
	
	public SkullMeta getSkullMeta() {
		return (SkullMeta)getItemMeta();
	}
	
	public LeatherArmorMeta getLeatherArmorMeta() {
		return (LeatherArmorMeta)getItemMeta();
	}
	
	public ISGen setItemMeta(ItemMeta im) {
		i.setItemMeta(im);
		return this;
	}
	
	public ISGen setOwner(String owner) {
		SkullMeta sm = getSkullMeta();
			sm.setOwner(owner);
		setItemMeta(sm);
		return this;
	}
	
	public String getOwner() {
		return getSkullMeta().getOwner();
	}
	
	public ISGen setColor(Color color) {
		LeatherArmorMeta lam = getLeatherArmorMeta();
			lam.setColor(color);
		setItemMeta(lam);
		return this;
	}
	
	public Color getColor() {
		return getLeatherArmorMeta().getColor();
	}
	
	public int getMaxStackSize() {
		return i.getMaxStackSize();
	}
	
	public Map<Enchantment, Integer> getEnchantments() {
		return i.getEnchantments();
	}
	
	public ISGen addEnchant(Enchantment e, int level) {
		i.addEnchantment(e, level);
		return this;
	}
	
	public ISGen addEnchantments(Map<Enchantment, Integer> enchantments) {
		i.addEnchantments(enchantments);
		return this;
	}
	
	public ISGen addUnsafeEnchant(Enchantment e, int level) {
		i.addUnsafeEnchantment(e, level);
		return this;
	}
	
	public ISGen addUnsafeEnchantments(Map<Enchantment, Integer> enchantments) {
		i.addUnsafeEnchantments(enchantments);
		return this;
	}
	
	public Set<ItemFlag> getItemFlags() {
		return getItemMeta().getItemFlags();
	}
	
	public ISGen addItemFlags(ItemFlag... flags) {
		ItemMeta im = getItemMeta();
			im.addItemFlags(flags);
		setItemMeta(im);
		return this;
	}
	
	public ISGen removeItemFlags(ItemFlag... flags) {
		ItemMeta im = getItemMeta();
			im.removeItemFlags(flags);
		setItemMeta(im);
		return this;
	}
	
	public boolean isUnbreakable() {
		return getItemMeta().spigot().isUnbreakable();
	}
	
	public ISGen setUnbreakable(boolean unbreakable) {
		ItemMeta im = getItemMeta();
			im.spigot().setUnbreakable(unbreakable);
		setItemMeta(im);
		return this;
	}
	
	public String getDisplayName() {
		return getItemMeta().getDisplayName();
	}
	
	public ISGen setDisplayName(String name) {
		ItemMeta im = getItemMeta();
			im.setDisplayName(color(name));
		setItemMeta(im);
		return this;
	}
	
	public List<String> getLore() {
		return getItemMeta().getLore();
	}
	
	public ISGen setLore(List<String> lore) {
		List<String> lores = lore;
		if(lores!=null) {
			for(int l = 0; l < lores.size(); l++) {
				lores.set(l, color(lores.get(l)));
			}
		        ItemMeta im = getItemMeta();
		        im.setLore(lores);
                        setItemMeta(im);
		}
		return this;
	}
	
	public Material getType() {
		return i.getType();
	}
	
	public ISGen setType(Material m) {
		i.setType(m);
		return this;
	}
	
	@SuppressWarnings("deprecation")
	public int getTypeId() {
		return i.getTypeId();
	}
	
	@SuppressWarnings("deprecation")
	public ISGen setTypeId(int id) {
		i.setTypeId(id);
		return this;
	}
	
	public int getAmount() {
		return i.getAmount();
	}
	
	public ISGen setAmount(int amount) {
		i.setAmount(amount);
		return this;
	}
	
	public short getDurability() {
		return i.getDurability();
	}

	public ISGen setDurability(short durability) {
		i.setDurability(durability);
		return this;
	}
        
        public String color(String s) {
                return ChatColor.translateAlternateColorCodes('&', s);
        }
}

