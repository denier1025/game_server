package root.utils;

import java.lang.reflect.Field;

/**
 * Created by Alexey on 11.06.2017.
 */
public final class ReflectionHelper {

    private ReflectionHelper() {
    }

    public static Object createInstance(String className) throws ReflectiveOperationException {
        try{
            return Class.forName(className).newInstance();
        } catch(ClassNotFoundException e) {
            throw new ReflectiveOperationException(e);
        }
    }

    public static void setFieldValue(Object obj, String fieldName, String fieldValue) throws ReflectiveOperationException {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            if (field.getType().equals(String.class)) {
                field.set(obj, fieldValue);
            } else if (field.getType().equals(int.class)) {
                field.set(obj, Integer.decode(fieldValue));
            }

            field.setAccessible(false);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new ReflectiveOperationException(e);
        }
    }

}
