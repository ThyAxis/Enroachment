package net.thyaxis.enroachment;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.thyaxis.enroachment.blocks.ModBlocks;
import net.thyaxis.enroachment.items.ModItems;
import net.thyaxis.enroachment.items.tab.EnroachmentTab;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Enroachment.MODID)
public class Enroachment
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "enroachment";


    public Enroachment()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();




        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        EnroachmentTab.register(modEventBus );
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {

    }
}
