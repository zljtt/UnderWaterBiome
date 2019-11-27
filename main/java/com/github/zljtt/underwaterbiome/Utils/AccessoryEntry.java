package com.github.zljtt.underwaterbiome.Utils;


import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.eventbus.api.Event;

public class AccessoryEntry<T extends Event>
{
	public static final AccessoryEntry<LivingDamageEvent> ON_ATTACK = new AccessoryEntry<LivingDamageEvent>("on_attack");
	public static final AccessoryEntry<LivingDamageEvent> ON_SUFFER_DAMAGE = new AccessoryEntry<LivingDamageEvent>("on_suffer_damage");
	public static final AccessoryEntry<PotionEvent> ON_PLAYER_ADD_EFFECT = new AccessoryEntry<PotionEvent>("on_player_add_effect");
	public static final AccessoryEntry<PotionEvent> ON_PLAYER_CHECK_EFFECT = new AccessoryEntry<PotionEvent>("on_player_check_effect");
	public static final AccessoryEntry<PotionEvent> ON_PLAYER_REMOVE_EFFECT = new AccessoryEntry<PotionEvent>("on_player_remove_effect");
	public static final AccessoryEntry<LivingAttackEvent> BEFORE_ATTACKED = new AccessoryEntry<LivingAttackEvent>("before_attacked");
	public static final AccessoryEntry<LivingAttackEvent> BEFORE_ATTACKING = new AccessoryEntry<LivingAttackEvent>("before_attacked");
	public static final AccessoryEntry<BreakSpeed> ON_DIGGING = new AccessoryEntry<BreakSpeed>("on_digging");
	public static final AccessoryEntry<LivingHealEvent> ON_HEALING = new AccessoryEntry<LivingHealEvent>("on_healing");
	public static final AccessoryEntry<PlayerTickEvent> ON_TICK = new AccessoryEntry<PlayerTickEvent>("on_tick");
	public static final AccessoryEntry<LivingKnockBackEvent> ON_KNOCKBACK = new AccessoryEntry<LivingKnockBackEvent>("on_knockback");

	public String name;
	public AccessoryEntry(String n)
	{
		name = n;
	}
}
