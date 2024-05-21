package org.styly.acanus.curios;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.item.ISpellbook;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ObjectHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.At;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.module.ItemModule;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.*;

public class BaseArtifactItem extends ModularItem  implements ICurioItem,IPresetSpellContainer{
    public static final String edgeKey = "card/edge";
    public static final String fillingKey = "card/filling";
    public static final String clothKey = "card/cloth";
    public static final String arcanumKey = "card/arcanum";
    public static final String fullerKey = "card/fuller";
    public static final String identifier = "card";

   @ObjectHolder(
            registryName = "item",
            value = "arcanus:card"
    )
    public static ModularItem instance;

    public BaseArtifactItem()  {
        super((new Item.Properties()).stacksTo(1).fireResistant().durability(0));
        this.majorModuleKeys = new String[]{"card/arcanum", "card/cloth"};
        this.minorModuleKeys = new String[]{"card/filling", "card/fuller","card/edge"};
        this.requiredModules = new String[]{"card/cloth", "card/arcanum"};
        this.updateConfig(false);
    }


    public void updateConfig(boolean canHone) {
        this.canHone=canHone;
    }
    public boolean isUnique() {
        return false;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
       return this.getAttributeModifiersCollapsed(stack);
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        if (!ISpellContainer.isSpellContainer(itemStack)) {
            var spellContainer = ISpellContainer.create(1, true, true);
            spellContainer.save(itemStack);
        }
    }
    public boolean isEquippedBy(@Nullable LivingEntity entity) {
        return entity != null && CuriosApi.getCuriosHelper().findFirstCurio(entity, this).isPresent();
    }
}
