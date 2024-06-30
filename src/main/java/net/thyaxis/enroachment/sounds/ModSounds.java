package net.thyaxis.enroachment.sounds;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thyaxis.enroachment.Enroachment;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Enroachment.MODID);


    public static final RegistryObject<SoundEvent> AMBIENT_SCARY = registerSoundEvents("ambient_scary");
    public static final RegistryObject<SoundEvent> ARM_FRACTURE = registerSoundEvents("arm_fracture");
    public static final RegistryObject<SoundEvent> CLOCK_AMBIENCE = registerSoundEvents("clock_ambience");
    public static final RegistryObject<SoundEvent> GHOST_AMBIENCE_NEW = registerSoundEvents("ghost_ambience_new");
    public static final RegistryObject<SoundEvent> GHOST_ATMOSPHERE = registerSoundEvents("ghost_atmosphere");
    public static final RegistryObject<SoundEvent> GLASS_SHATTER = registerSoundEvents("glass_shatter");
    public static final RegistryObject<SoundEvent> ROOM_AMBIENCE = registerSoundEvents("room_ambience");
    public static final RegistryObject<SoundEvent> WOOD_CRACKING = registerSoundEvents("wood_cracking");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Enroachment.MODID, name)));
    }


    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
