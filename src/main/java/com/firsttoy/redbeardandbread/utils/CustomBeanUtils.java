package com.firsttoy.redbeardandbread.utils;

import com.firsttoy.redbeardandbread.exception.CustomRuntimeException;
import com.firsttoy.redbeardandbread.exception.Exceptions;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

@Component
public class CustomBeanUtils<T> {

    public T copyProperties(T target, T source) {
        if (target == null || source == null ) {
            throw new CustomRuntimeException(Exceptions.NULL_RESOURCE);
        }

        if (target.getClass() != source.getClass()) {
            throw new CustomRuntimeException(Exceptions.TYPE_DIFFERENCE);
        }

        final BeanWrapper tar = PropertyAccessorFactory.forBeanPropertyAccess(target);
        final BeanWrapper src = PropertyAccessorFactory.forBeanPropertyAccess(source);

        for (final Field property : source.getClass().getDeclaredFields()) {
            Object sourceProperty = src.getPropertyValue(property.getName());

            if (sourceProperty != null && !(sourceProperty instanceof Collection<?>)) {
                tar.setPropertyValue(property.getName(), sourceProperty);
            }

        }

        //todo : 컬렉션 업데이트(itemOption)하기
        return target;
    }
}
