package org.styly.arcanus.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import static org.styly.arcanus.Arcanus.MODID;

public class CreativeTabRegistry {
    private static final DeferredRegister<CreativeModeTab> CreativeTabRegister = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final Holder<CreativeModeTab> ARCANUS_TAB = CreativeTabRegister.register("items", () -> CreativeModeTab.builder()
            // Set name of tab to display
            .title(Component.translatable("item_group." + MODID + ".items"))
            // Set icon of creative tab
            .icon(() -> new ItemStack(ModItems.Card.get()))
            // Add default items to tab
            .displayItems((params, output) -> {
                output.accept(ModItems.Card.get());
                output.accept(ModItems.BandOfBlessing.get());
                output.accept(ModItems.DiademOftheDragon.get());
                output.accept(ModItems.EmblemOfEvoker.get());
                output.accept(ModItems.GarlandOfGaian.get());
                output.accept(ModItems.LocketOfLighting.get());
                output.accept(ModItems.PendantOfPyromania.get());
                output.accept(ModItems.WreathOfWinter.get());
                output.accept(ModItems.NecklaceOfTheNecromancer.get());

                output.accept(ModItems.eldritch_rune.get());
                output.accept(ModItems.ARCANE_SILK.get());

                output.accept(ModItems.RITUAL_TABLE_ITEM);
                output.accept(ModItems.ETERNAL_STELLA);
                output.accept(ModItems.MANA_VIAL);
                output.accept(ModItems.EMPTY_VIAL);

                output.accept(ModItems.NETHERITE_MAGE_HELMET.get());
                output.accept(ModItems.NETHERITE_MAGE_CHESTPLATE.get());
                output.accept(ModItems.NETHERITE_MAGE_LEGGINGS.get());
                output.accept(ModItems.NETHERITE_MAGE_BOOTS.get());
                output.accept(ModItems.RING_OF_FLIGHT.get());

                output.accept(ModItems.EXCALIBUR.get());
            })
            .build()
    );

    public static void register(IEventBus eventBus) {
        CreativeTabRegister.register(eventBus); // Made A cool spelling Bug causing infinite Recursion
    }
}
