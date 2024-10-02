package org.styly.arcanus.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class ArcanusCodecs {
    public static final Codec<ItemMana> ItemManaCodec = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("limit").forGetter(ItemMana::getLimit),
                    Codec.INT.fieldOf("stored").forGetter(ItemMana::getMana)
            ).apply(instance, ItemMana::new)
    );
}
