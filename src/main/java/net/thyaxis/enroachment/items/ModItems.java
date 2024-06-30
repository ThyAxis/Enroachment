package net.thyaxis.enroachment.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thyaxis.enroachment.Enroachment;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Enroachment.MODID);

    public static final RegistryObject<Item> STAR = ITEMS.register("star",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).setNoRepair().fireResistant()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}