package org.styly.acanus.events;

import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.compat.Curios;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import org.styly.acanus.Arcanus;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

@EventBusSubscriber(modid = Arcanus.MODID)
public class RegisterISSslot {
    @SubscribeEvent
    public static void Register(SpellSelectionManager.SpellSelectionEvent event){
        String equipmentSlot = Curios.RING_SLOT;
        ItemStack itemStack = CuriosApi.getCuriosHelper().findCurio(event.getEntity(), Curios.RING_SLOT, 0).map(SlotResult::stack).orElse(null);
        if (ISpellContainer.isSpellContainer(itemStack)) {
            var spellContainer = ISpellContainer.get(itemStack);
            if (spellContainer.isSpellWheel() && (!spellContainer.mustEquip() )) {
                var activeSpells = spellContainer.getActiveSpells();
                for (int i = 0; i < activeSpells.size(); i++) {
                    var spellSlot = activeSpells.get(i);
                    event.addSelectionOption(spellSlot.spellData(), equipmentSlot, i, event.getManager().getAllSpells().size());
                }
            }
        }
    }

}
