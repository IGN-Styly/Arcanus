package org.styly.arcanus.events;

import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.item.TempManaRing;
import org.styly.arcanus.registry.ModEffects;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;

public class CancelSpell {
    @SubscribeEvent
    public static void SpellPreCastEvent(SpellPreCastEvent event){
        if (event.getEntity().hasEffect(ModEffects.MAGIC_BLOCKED)) {
            event.setCanceled(true);
        }
        var player = event.getEntity();
        Arcanus.LOGGER.warn("Casting");
        CuriosApi.getCuriosHelper().getEquippedCurios(player).ifPresent(curioHandler -> {
            for (int i = 0; i < curioHandler.getSlots(); i++) {
                ItemStack curioItem = curioHandler.getStackInSlot(i);

                // Check if the item is the one you want to apply durability change to
                if (curioItem.getItem() instanceof TempManaRing) {
                    Arcanus.LOGGER.warn("Damaging");
                    // Reduce durability by 1
                    curioItem.hurtAndBreak(1, player, curioItem.getEquipmentSlot());
                    break; // Exit once you've processed one curio
                }
            }
        });
    }
}