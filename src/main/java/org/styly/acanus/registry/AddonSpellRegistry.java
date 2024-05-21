package org.styly.acanus.registry;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.styly.acanus.Arcanus;
import org.styly.acanus.spells.ManaSmite;
import org.styly.acanus.spells.TheFool;


public class AddonSpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, Arcanus.MODID);

    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }
    public static final RegistryObject<AbstractSpell> theFool= SPELLS.register("the_fool", TheFool::new);
    public static final RegistryObject<AbstractSpell> ManaSmite = SPELLS.register("mana_smite", ManaSmite::new);
    public static RegistryObject<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }
}
