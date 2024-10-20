package org.styly.arcanus.registry;

import com.jcraft.jorbis.Block;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.item.weapons.ExtendedWeaponTier;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Unbreakable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.curios.BasicAttributeCurios;
import org.styly.arcanus.item.*;
import org.styly.arcanus.util.Curios;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Arcanus.MODID);
    //public static final DeferredItem<Item> BaseArtifact=ITEMS.register(BaseArtifactItem.identifier, BaseArtifactItem::new);
    public static final DeferredItem<Item> Card = ITEMS.register("curios_card", () -> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withCardAttributes(new AttributeContainer(AttributeRegistry.MAX_MANA, 100, AttributeModifier.Operation.ADD_VALUE)));
    //Runes
    public static final DeferredItem<Item> eldritch_rune = (DeferredItem<Item>) ITEMS.register("eldritch_rune", () -> new Item(new Item.Properties()));
    // Arcane Amulets, idea by @Amadhe

    public static final DeferredItem<Item> BandOfBlessing = ITEMS.register("band_of_blessed", () -> new BasicAttributeCurios(new Item.Properties().stacksTo(1)).withAttributes(Curios.NECKLACE_SLOT, new AttributeContainer(AttributeRegistry.HOLY_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> DiademOftheDragon = ITEMS.register("diadem_of_the_dragon", () -> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes(Curios.NECKLACE_SLOT, new AttributeContainer(AttributeRegistry.ENDER_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> EmblemOfEvoker = ITEMS.register("emblem_of_the_evoker", () -> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes(Curios.NECKLACE_SLOT, new AttributeContainer(AttributeRegistry.EVOCATION_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> GarlandOfGaian = ITEMS.register("garland_of_gaia", () -> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes(Curios.NECKLACE_SLOT, new AttributeContainer(AttributeRegistry.NATURE_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> LocketOfLighting = ITEMS.register("locket_of_lightning", () -> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes(Curios.NECKLACE_SLOT, new AttributeContainer(AttributeRegistry.LIGHTNING_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> NecklaceOfTheNecromancer = ITEMS.register("necklace_of_the_necromancer", () -> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes(Curios.NECKLACE_SLOT, new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> PendantOfPyromania = ITEMS.register("pyromania_pendent", () -> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes(Curios.NECKLACE_SLOT, new AttributeContainer(AttributeRegistry.FIRE_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
    public static final DeferredItem<Item> WreathOfWinter = ITEMS.register("wreath_of_winter", () -> new BasicAttributeCurios(new Item.Properties().stacksTo(1).fireResistant()).withAttributes(Curios.NECKLACE_SLOT, new AttributeContainer(AttributeRegistry.ICE_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));

    //ritual
    public static final DeferredItem<BlockItem> RITUAL_TABLE_ITEM = ITEMS.registerSimpleBlockItem(ArcanusBlockRegistry.RITUAL_TABLE_BLOCK);

    //cool materials
    public static final DeferredItem<Item> ARCANE_SILK = (DeferredItem<Item>) ITEMS.register("arcane_silk", () -> new Item(new Item.Properties().fireResistant().stacksTo(16).rarity(Rarity.EPIC)));

    //Cool rings
    public static final DeferredHolder<Item, FlightRing> RING_OF_FLIGHT = ITEMS.register("flight_ring", FlightRing::new);
    public static final DeferredItem<Item> RING_OF_MANA = ITEMS.register("mana_ring", () -> new TempManaRing(new Item.Properties().stacksTo(1).fireResistant().durability(4)).withAttributes(Curios.RING_SLOT, new AttributeContainer(AttributeRegistry.MAX_MANA, 10000, AttributeModifier.Operation.ADD_VALUE)));
    // Lore Armor
    public static final DeferredItem<Item> NETHERITE_MAGE_HELMET = ITEMS.register("netherite_mage_helmet", () -> new DarkMageArmourItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment().fireResistant()));
    public static final DeferredItem<Item> NETHERITE_MAGE_CHESTPLATE = ITEMS.register("netherite_mage_chestplate", () -> new DarkMageArmourItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment().fireResistant()));
    public static final DeferredItem<Item> NETHERITE_MAGE_LEGGINGS = ITEMS.register("netherite_mage_leggings", () -> new DarkMageArmourItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment().fireResistant()));
    public static final DeferredItem<Item> NETHERITE_MAGE_BOOTS = ITEMS.register("netherite_mage_boots", () -> new DarkMageArmourItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment().fireResistant()));

    // The Seven
    public static final DeferredHolder<Item, Item> EXCALIBUR = ITEMS.register("excalibur", () -> new MagicSwordItem(ArcanusWeaponTiers.Legendary, ItemPropertiesHelper.equipment().fireResistant().durability(0).rarity(Rarity.EPIC).attributes(ExtendedSwordItem.createAttributes(ArcanusWeaponTiers.Legendary)).rarity(Rarity.EPIC), SpellDataRegistryHolder.of(new SpellDataRegistryHolder(AddonSpellRegistry.Recollection, 1))));

    //replacement for air because mojang dosen't know shit.
    public static final DeferredItem<Item> AIR = ITEMS.register("air", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
