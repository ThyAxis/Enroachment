package net.thyaxis.enroachment.effects.particle;

import net.minecraft.client.*;
import net.minecraft.client.player.*;
import net.minecraft.core.particles.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.event.*;
import net.minecraftforge.event.entity.item.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;
import team.lodestar.lodestone.registry.common.particle.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;

import java.awt.Color;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AdditiveParticle {

    @SubscribeEvent
    public static void onItemToss(ItemTossEvent event) {
        ItemEntity itemEntity = event.getEntity();
        ItemStack itemStack = itemEntity.getItem();
        Level level = itemEntity.level();

        // Check if the dropped item is dirt
        if (itemStack.getItem() == Items.DIRT) {
            spawnExampleParticles(level, itemEntity.position());
        }
    }

    public static void spawnExampleParticles(Level level, Vec3 pos) {
        Color startingColor = new Color(100, 0, 100);
        Color endingColor = new Color(0, 100, 200);
        WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
                .setScaleData(GenericParticleData.create(0.5f, 0).build())
                .setTransparencyData(GenericParticleData.create(0.25f, 0.25f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.CIRC_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((level.getGameTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(20)
                .addMotion(0, 0.01f, 0)
                .enableNoClip()
                .spawn(level, pos.x, pos.y, pos.z);
    }
}
