package org.styly.acanus.registry;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.armor.NetheriteMageArmorItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.styly.acanus.Arcanus;
import org.styly.acanus.curios.BaseArtifactItem;
import org.styly.acanus.curios.BasicAttributeCurios;
import org.styly.acanus.item.DarkMageArmourItem;
import org.styly.acanus.item.FlightRing;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS= DeferredRegister.create(ForgeRegistries.ITEMS, Arcanus.MODID);
    public static final RegistryObject<Item> BaseArtifact=ITEMS.register(BaseArtifactItem.identifier, BaseArtifactItem::new);
    public static final RegistryObject<Item> Card = ITEMS.register("curios_card",()->new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant(),AttributeRegistry.MAX_MANA.get(), new AttributeModifier("mana",100, AttributeModifier.Operation.ADDITION)));
    //Runes
    public static final RegistryObject<Item> eldritch_rune=ITEMS.register("eldritch_rune",()->new Item(new Item.Properties()));
    // Arcane Amulets, idea by @Amadhe

    public static final RegistryObject<Item> BandOfBlessing = ITEMS.register("band_of_blessed",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1),AttributeRegistry.HOLY_SPELL_POWER.get(), new AttributeModifier("holy",0.15, AttributeModifier.Operation.MULTIPLY_TOTAL)));
    public static final RegistryObject<Item> DiademOftheDragon = ITEMS.register("diadem_of_the_dragon",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant(),AttributeRegistry.ENDER_SPELL_POWER.get(), new AttributeModifier("ender",0.15, AttributeModifier.Operation.MULTIPLY_TOTAL)));
    public static final RegistryObject<Item> EmblemOfEvoker = ITEMS.register("emblem_of_the_evoker",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant(),AttributeRegistry.EVOCATION_SPELL_POWER.get(), new AttributeModifier("evocation",0.15, AttributeModifier.Operation.MULTIPLY_TOTAL)));
    public static final RegistryObject<Item> GarlandOfGaian = ITEMS.register("garland_of_gaia",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant(),AttributeRegistry.NATURE_SPELL_POWER.get(), new AttributeModifier("nature",0.15, AttributeModifier.Operation.MULTIPLY_TOTAL)));
    public static final RegistryObject<Item> LocketOfLighting = ITEMS.register("locket_of_lightning",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant(),AttributeRegistry.LIGHTNING_SPELL_POWER.get(), new AttributeModifier("lightning",0.15, AttributeModifier.Operation.MULTIPLY_TOTAL)));
    public static final RegistryObject<Item> NecklaceOfTheNecromancer = ITEMS.register("necklace_of_the_necromancer",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant(),AttributeRegistry.BLOOD_SPELL_POWER.get(), new AttributeModifier("blood",0.15, AttributeModifier.Operation.MULTIPLY_TOTAL)));
    public static final RegistryObject<Item> PendantOfPyromania = ITEMS.register("pyromania_pendent",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant(),AttributeRegistry.FIRE_SPELL_POWER.get(), new AttributeModifier("fire",0.15, AttributeModifier.Operation.MULTIPLY_TOTAL)));
    public static final RegistryObject<Item> WreathOfWinter = ITEMS.register("wreath_of_winter",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant(),AttributeRegistry.ICE_SPELL_POWER.get(), new AttributeModifier("ice",0.15, AttributeModifier.Operation.MULTIPLY_TOTAL)));


    //Cool ring
    public static final RegistryObject<Item> RING_OF_FLIGHT = ITEMS.register("flight_ring", FlightRing::new);
    // Lore Armor
    public static final RegistryObject<Item> NETHERITE_MAGE_HELMET = ITEMS.register("netherite_mage_helmet", () -> new DarkMageArmourItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment()));
    public static final RegistryObject<Item> NETHERITE_MAGE_CHESTPLATE = ITEMS.register("netherite_mage_chestplate", () -> new DarkMageArmourItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment()));
    public static final RegistryObject<Item> NETHERITE_MAGE_LEGGINGS = ITEMS.register("netherite_mage_leggings", () -> new DarkMageArmourItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment()));
    public static final RegistryObject<Item> NETHERITE_MAGE_BOOTS = ITEMS.register("netherite_mage_boots", () -> new DarkMageArmourItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment()));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
