package org.styly.arcanus.item;

import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.item.armor.ExtendedArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.styly.arcanus.registry.ModEffects;

public abstract class ImbuableDarkChestplateArmorItem extends ExtendedArmorItem implements IPresetSpellContainer {
    public ImbuableDarkChestplateArmorItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties settings, AttributeContainer... attributes) {
        super(material, type, settings,attributes);
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }


    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(!pLevel.isClientSide&&pEntity instanceof Player&&pLevel.isNight()){
            if(hasFullSuitOfArmorOn((Player) pEntity)){
                Player player = (Player) pEntity;
                player.addEffect(new MobEffectInstance(ModEffects.FULL_MOON,60));
            }
        }
    }

    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        if (itemStack.getItem() instanceof ArmorItem armorItem && armorItem.getType() == ArmorItem.Type.CHESTPLATE) {
            if (!ISpellContainer.isSpellContainer(itemStack)) {
                var spellContainer = ISpellContainer.create(1, true, true);
                spellContainer.getAllSpells();
            }
        }
    }
}

