package net.thyaxis.enroachment.items.tab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.thyaxis.enroachment.Enroachment;
import net.thyaxis.enroachment.blocks.ModBlocks;
import net.thyaxis.enroachment.items.ModItems;

public class EnroachmentTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB_DEFERRED_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Enroachment.MODID);
    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TAB_DEFERRED_REGISTER.register("tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STAR.get()))
                    .title(Component.translatable("enroachment.tab"))
                    .displayItems(((itemDisplayParameters, output) -> {



                        // ITEMS
                        output.accept(ModItems.STAR.get());


                        // BLOCKS
                        output.accept(ModBlocks.VOID_VINE.get());


                    })).build());


    public static void register(IEventBus bus) {
        CREATIVE_MODE_TAB_DEFERRED_REGISTER.register(bus);
    }
}
