package org.styly.arcanus.registry;

import com.mojang.serialization.Codec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.styly.arcanus.Arcanus;

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
