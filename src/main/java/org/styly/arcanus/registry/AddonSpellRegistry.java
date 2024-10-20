package org.styly.arcanus.registry;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.spells.DarkVeil;
import org.styly.arcanus.spells.Recollection;
import org.styly.arcanus.spells.TheFool;

import java.util.function.Supplier;


public class AddonSpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, Arcanus.MODID);

    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }

    public static final Supplier<AbstractSpell> theFool = SPELLS.register("the_fool", TheFool::new);
    public static final Supplier<AbstractSpell> Recollection = SPELLS.register("recollection", Recollection::new);
    public static final Supplier<AbstractSpell> DarkVeil = SPELLS.register("dark_veil", DarkVeil::new);

    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }
}
