package org.comroid.dux.type;

import org.comroid.api.HeldType;
import org.comroid.api.Named;
import org.comroid.uniform.node.impl.StandardValueType;

import java.util.function.Function;

public class EnumHeldType<R extends Enum<R> & Named, USR, MSG> implements HeldType<R> {
    private final Class<R> enumType;
    private final Function<String, R> converter;

    @Override
    public Function<String, R> getConverter() {
        return converter;
    }

    @Override
    public String getName() {
        return enumType.getSimpleName();
    }

    public EnumHeldType(Class<R> enumType, Function<String, R> parser) {
        this.enumType = enumType;
        this.converter = parser;
    }

    @Override
    public <T> T convert(R value, HeldType<T> toType) {
        if (toType.equals(StandardValueType.STRING))
            //noinspection unchecked
            return (T) value.getName();
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<R> getTargetClass() {
        return enumType;
    }
}
