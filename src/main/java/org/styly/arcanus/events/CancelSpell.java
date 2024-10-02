package org.styly.arcanus.events;

import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import net.neoforged.bus.api.SubscribeEvent;
import org.styly.arcanus.registry.ModEffects;

public class CancelSpell {
    @SubscribeEvent
    public static void SpellPreCastEvent(SpellPreCastEvent event){
        if (event.getEntity().hasEffect(ModEffects.MAGIC_BLOCKED)) {
            event.setCanceled(true);
        }
    }
}
