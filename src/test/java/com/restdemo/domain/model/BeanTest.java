package com.restdemo.domain.model;

import com.google.gson.Gson;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Class BeanTest.
 */
public abstract class BeanTest
{

    /**
     * Test check accessor.
     * 
     * @param obj object
     * @throws AssertionError assert error
     */
    public void checkAccessor(Object obj)
    {
        PropertyDescriptor[] listPropertyDescriptors = BeanUtils.getPropertyDescriptors(obj.getClass());

        PropertyDescriptor[] listPropertyToIgnore = BeanUtils.getPropertyDescriptors(Object.class);
        /*
         * listPropertyDescriptors
         */
        for (PropertyDescriptor descriptor : listPropertyDescriptors)
        {
            if (!ArrayUtils.contains(listPropertyToIgnore, descriptor))
            {
                /*
                 * getPropertyType
                 */
                Class<?> clazz = descriptor.getPropertyType();

                Object arg = null;

                arg = checkClassType(clazz, arg);
                try
                {
                    /*
                     * getWriteMethod
                     */
                    Method writeMethod = descriptor.getWriteMethod();
                    /*
                     * getReadMethod
                     */
                    Method readMethod = descriptor.getReadMethod();
                    /*
                     * control writeMethod
                     */
                    if (writeMethod != null)
                    {
                        /*
                         * setter with argument
                         */
                        writeMethod.invoke(obj, arg);
                    }
                    /*
                     * control readMethod
                     */
                    if (readMethod != null)
                    {
                        /*
                         * getter
                         */
                        Object value = readMethod.invoke(obj);
                        /*
                         * setter
                         */
                        if (writeMethod != null)
                        {
                            /*
                             * check response value and request value are the same
                             */
                            assertTrue((value == null && arg == null) || (value != null && value.equals(arg)));
                        }
                    }
                }
                catch (IllegalAccessException e)
                {
                    /*
                     * throws AssertionError
                     */
                    throw new AssertionError(e);
                }
                catch (InvocationTargetException e)
                {
                    /*
                     * throws AssertionError
                     */
                    throw new AssertionError(e);
                }
            }
        }
        assertNotNull(obj);
        /*
         * hashcode
         */
        assertNotNull(obj.hashCode());
        /*
         * toString
         */
        assertNotNull(obj.toString());
        /*
         * Equals
         */
        assertFalse(obj.equals(null));
        assertTrue(obj.equals(obj));
        //deep clone
        Gson gson = new Gson();
        Object deepCopy = gson.fromJson(gson.toJson(obj), obj.getClass());
        obj.equals(deepCopy);
    }

    /**
     * Method Check class type.
     *
     * @param clazz class
     * @param arg argument
     * @return object
     */
    private Object checkClassType(Class<?> clazz, Object arg)
    {
        /*
         * boolean
         */
        if (clazz.isAssignableFrom(boolean.class))
        {
            arg = Boolean.TRUE;
        }
        /*
         * long
         */
        else if (clazz.isAssignableFrom(long.class))
        {
            arg = 0l;
        }
        /*
         * short
         */
        else if (clazz.isAssignableFrom(short.class))
        {
            arg = 0;
        }
        /*
         * int
         */
        else if (clazz.isAssignableFrom(int.class))
        {
            arg = 0;
        }
        /*
         * float
         */
        else if (clazz.isAssignableFrom(float.class))
        {
            arg = 0f;
        }
        /*
         * double
         */
        else if (clazz.isAssignableFrom(double.class))
        {
            arg = 0d;
        }
        /*
         * list
         */
        else if (clazz.isAssignableFrom(List.class))
        {
            arg = new ArrayList<>();
        }
        /*
         * map
         */
        else if (clazz.isAssignableFrom(Map.class))
        {
            arg = new HashMap<>();
        }
        /*
         * string
         */
        else if (clazz.isAssignableFrom(String.class))
        {
            arg = "test";
        }
        /*
         * char
         */
        else if (clazz.isAssignableFrom(char.class))
        {
            arg = 't';
        }
        return arg;
    }
}
