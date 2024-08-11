package org.styly.acanus.registry;

import net.neoforged.bus.api.IEventBus;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.armor.NetheriteMageArmorItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.styly.acanus.Arcanus;
import org.styly.acanus.curios.BasicAttributeCurios;
import org.styly.acanus.item.AttributeContainer;
import org.styly.acanus.item.DarkMageArmourItem;
import org.styly.acanus.item.FlightRing;
import org.styly.acanus.util.Curios;

public class ModItems {

    public static final DeferredRegister.Items ITEMS= DeferredRegister.createItems(Arcanus.MODID);
    //public static final DeferredItem<Item> BaseArtifact=ITEMS.register(BaseArtifactItem.identifier, BaseArtifactItem::new);
    public static final DeferredItem<Item> Card = ITEMS.register("curios_card",()->new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withCardAttributes(new AttributeContainer(AttributeRegistry.MAX_MANA,100, AttributeModifier.Operation.ADD_VALUE)));
    //Runes
    public static final DeferredItem<Item> eldritch_rune= (DeferredItem<Item>) ITEMS.register("eldritch_rune",()->new Item(new Item.Properties()));
    // Arcane Amulets, idea by @Amadhe

    public static final DeferredItem<Item> BandOfBlessing = ITEMS.register("band_of_blessed",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1)).withAttributes( Curios.NECKLACE_SLOT,new AttributeContainer(AttributeRegistry.HOLY_SPELL_POWER,0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> DiademOftheDragon = ITEMS.register("diadem_of_the_dragon",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes(Curios.NECKLACE_SLOT, new AttributeContainer(AttributeRegistry.ENDER_SPELL_POWER,0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> EmblemOfEvoker = ITEMS.register("emblem_of_the_evoker",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes( Curios.NECKLACE_SLOT,new AttributeContainer(AttributeRegistry.EVOCATION_SPELL_POWER,0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> GarlandOfGaian = ITEMS.register("garland_of_gaia",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes(Curios.NECKLACE_SLOT, new AttributeContainer(AttributeRegistry.NATURE_SPELL_POWER,0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> LocketOfLighting = ITEMS.register("locket_of_lightning",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes(Curios.NECKLACE_SLOT,new AttributeContainer(AttributeRegistry.LIGHTNING_SPELL_POWER,0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> NecklaceOfTheNecromancer = ITEMS.register("necklace_of_the_necromancer",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes(Curios.NECKLACE_SLOT,new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER,0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> PendantOfPyromania = ITEMS.register("pyromania_pendent",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes(Curios.NECKLACE_SLOT, new AttributeContainer(AttributeRegistry.FIRE_SPELL_POWER,0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> WreathOfWinter = ITEMS.register("wreath_of_winter",()-> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes(Curios.NECKLACE_SLOT,new AttributeContainer(AttributeRegistry.ICE_SPELL_POWER,0.15,AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));


    //cool materials
    public static final DeferredItem<Item> ARCANESILK = (DeferredItem<Item>) ITEMS.register("arcane_silk",()->new Item(new Item.Properties().fireResistant().stacksTo(16).rarity(Rarity.EPIC)));

    //Cool ring
    public static final DeferredHolder<Item, FlightRing> RING_OF_FLIGHT = ITEMS.register("flight_ring", FlightRing::new);
    // Lore Armor
    public static final DeferredItem<Item> NETHERITE_MAGE_HELMET = ITEMS.register("netherite_mage_helmet", () -> new DarkMageArmourItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment()));
    public static final DeferredItem<Item> NETHERITE_MAGE_CHESTPLATE = ITEMS.register("netherite_mage_chestplate", () -> new DarkMageArmourItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment()));
    public static final DeferredItem<Item> NETHERITE_MAGE_LEGGINGS = ITEMS.register("netherite_mage_leggings", () -> new DarkMageArmourItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment()));
    public static final DeferredItem<Item> NETHERITE_MAGE_BOOTS = ITEMS.register("netherite_mage_boots", () -> new DarkMageArmourItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment()));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
