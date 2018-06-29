package com.tarde.merger;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.apache.commons.lang3.ClassUtils.isPrimitiveOrWrapper;

public class ObjectMerger {
    private final List<Object> selfObjects;
    private final Object source;
    private final Object target;

    private ObjectMerger(Object source, Object target) {
        this.source = source;
        this.target = target;
        this.selfObjects = new ArrayList<>();
    }

    public static ObjectMerger mergerOf(Object source, Object target) {
        return new ObjectMerger(source, target);
    }

    public final void merge() {
        try {
            merge(source, target);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException("Merge error: ", e);
        }
    }

    private void merge(Object source, Object target) throws IllegalAccessException, NoSuchFieldException {
        selfObjects.add(source);

        Field[] declaredFields = source.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);

            Object fieldValue = declaredField.get(source);
            if (fieldValue == null || selfObjects.contains(fieldValue)) {
                continue;
            }

            Class<?> declaredFieldType = declaredField.getType();
            if (isJdkType(declaredField)) {
                Field targetField = target.getClass().getDeclaredField(declaredField.getName());
                targetField.setAccessible(true);

                targetField.set(target, fieldValue);
                continue;
            }

            if (Collection.class.isAssignableFrom(declaredFieldType)) {
                Iterable sourceCollection = (Iterable) declaredField.get(source);
                Iterable targetCollection = (Iterable) declaredField.get(target);

                merge(sourceCollection, targetCollection);
                continue;
            }

            merge(declaredField.get(source), declaredField.get(target));
        }
    }

    private boolean isJdkType(Field field) {
        Class<?> declaredFieldType = field.getType();
        String fieldTypeName = declaredFieldType.getName();

        return isPrimitiveOrWrapper(declaredFieldType)
        || fieldTypeName.equals(String.class.getName())
        || fieldTypeName.equals(BigDecimal.class.getName());
    }

    private void merge(Iterable source, Iterable target) throws NoSuchFieldException, IllegalAccessException {
        Iterator sourceIterator = source.iterator();
        Iterator targetIterator = target.iterator();

        while (sourceIterator.hasNext()) {
            merge(sourceIterator.next(), targetIterator.next());
        }
    }
}
