package org.styly.acanus.registry;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.styly.acanus.Arcanus;
import org.styly.acanus.spells.ManaCombustion;
import org.styly.acanus.spells.ManaSmite;
import org.styly.acanus.spells.TheFool;

import java.util.function.Supplier;


public class AddonSpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, Arcanus.MODID);

    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }
    public static final Supplier<AbstractSpell> theFool= SPELLS.register("the_fool", TheFool::new);
    public static final Supplier<AbstractSpell> ManaSmite = SPELLS.register("mana_smite", ManaSmite::new);
    public static final Supplier<AbstractSpell> ManaCombustion = SPELLS.register("mana_combustion", ManaCombustion::new);

    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }
}
