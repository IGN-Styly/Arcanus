package org.styly.arcanus.registry;

import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.component.ArcanusCodecs;
import org.styly.arcanus.component.ItemMana;

import java.util.function.UnaryOperator;

public class ArcanusComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES=
            DeferredRegister.createDataComponents(Arcanus.MODID);

    public static final DeferredHolder<DataComponentType<?>,DataComponentType<ItemMana>> ItemMana = register("item_mana", builder->builder.persistent(ArcanusCodecs.ItemManaCodec).cacheEncoding());


    public static <T> DeferredHolder<DataComponentType<?>,DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> buildOperator){
        return DATA_COMPONENT_TYPES.register(name,()->buildOperator.apply(DataComponentType.builder()).build());
    }
    public static void register(IEventBus eventBus){
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
