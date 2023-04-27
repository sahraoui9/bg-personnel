package bgpersonnel.budget.service;


import java.lang.reflect.Method;

public class GeneratorHelper {

    public static Object getFieldValueFromObject(Object obj, String fieldName) {
        try {
            Class<?> clazz = obj.getClass();
            String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method getterMethod = clazz.getMethod(getterName);
            return getterMethod.invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException("Error getting field value for field " + fieldName, e);
        }
    }
}
