package com.lucidity.geigercraft;

import net.minecraft.util.DamageSource;

public class RadiationDamageSource {
	public static final DamageSource radiationBurn = new DamageSource("radiationBurn").setDamageBypassesArmor().setDamageIsAbsolute();
	public static final DamageSource radiationSickness = new DamageSource("radiationSickness").setDamageBypassesArmor().setDamageIsAbsolute();
}
