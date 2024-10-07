package org.styly.arcanus.registry;

import com.mojang.serialization.Codec;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.styly.arcanus.Arcanus;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ArcanusDataAttachments {
    // Create the DeferredRegister for attachment types
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Arcanus.MODID);

    // Serialization via codec
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Float>> DAMAGE_ABSORB = ATTACHMENT_TYPES.register(
            "damage_absorb", () -> AttachmentType.builder(() -> 0.0f).serialize(Codec.FLOAT).build()
    );
    public static void register(IEventBus eventBus){
        ATTACHMENT_TYPES.register(eventBus);
    }
}
