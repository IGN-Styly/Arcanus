package org.styly.acanus.events;

import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.compat.Curios;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import org.styly.acanus.Arcanus;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

@Mod.EventBusSubscriber(modid = Arcanus.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RegisterISSslot {
    @SubscribeEvent
    public static void Register(SpellSelectionManager.SpellSelectionEvent event){
        String equipmentSlot = Curios.RING_SLOT;
        ItemStack itemStack = CuriosApi.getCuriosHelper().findCurio(event.getEntity(), Curios.RING_SLOT, 0).map(SlotResult::stack).orElse(null);
        if (ISpellContainer.isSpellContainer(itemStack)) {
            var spellContainer = ISpellContainer.get(itemStack);
            if (spellContainer.spellWheel() && (!spellContainer.mustEquip() )) {
                var activeSpells = spellContainer.getActiveSpells();
                for (int i = 0; i < activeSpells.size(); i++) {
                    var spellSlot = activeSpells.get(i);
                    event.addSelectionOption(spellSlot, equipmentSlot, i, event.getManager().getAllSpells().size());
                }
            }
        }
    }

}
