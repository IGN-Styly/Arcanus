package org.styly.acanus.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.styly.acanus.Arcanus;

import static org.styly.acanus.Arcanus.MODID;

public class CreativeTabRegistry {
    private static final DeferredRegister<CreativeModeTab> CreativeTabRegister= DeferredRegister.create(Registries.CREATIVE_MODE_TAB,MODID);
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CreativeTabRegister.register("items", () -> CreativeModeTab.builder()
            // Set name of tab to display
            .title(Component.translatable("item_group." + MODID + ".items"))
            // Set icon of creative tab
            .icon(() -> new ItemStack(ModItems.BaseArtifact.get()))
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
                output.accept(ModItems.ARCANESILK.get());
                
                output.accept(ModItems.NETHERITE_MAGE_HELMET.get());
                output.accept(ModItems.NETHERITE_MAGE_CHESTPLATE.get());
                output.accept(ModItems.NETHERITE_MAGE_LEGGINGS.get());
                output.accept(ModItems.NETHERITE_MAGE_BOOTS.get());
                output.accept(ModItems.RING_OF_FLIGHT.get());
            })
            .build()
    );

    public static void register(IEventBus eventBus){
        CreativeTabRegister.register(eventBus); // Made A cool spelling Bug causing infinite Recursion
    }
}
