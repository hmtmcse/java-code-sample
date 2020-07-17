package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
//https://gist.github.com/isopropylcyanide/833e435cb261827e2d5af772ce8264a4

public class ObjectMapperUtil {

    /**
     * The object field mapper. xposes a mapper function that takes in a object of
     * any type and generates a map of <property_name, value> Property name should
     * be prefixed with parent property name and a "." in case of nesting Value
     * should always be of type "String". We first populate an initial map with
     * nested values. Then we use a utility function that helps us process the map
     * in a certain format
     *
     * Please note that for testing under Jacoco its recommended to filter out
     * synthetic fields as Jacoco plugs in some of its own during reflection
     *
     * @param element
     *            the element
     * @return the map
     */
    public static Map<String, String> objectFieldMapper(Object element) {
        Map<String, String> fieldMap = new HashMap<>();
        final String root = element.getClass().getSimpleName();
        Field[] fields = element.getClass().getDeclaredFields();
        Arrays.stream(fields).filter(field -> !field.isSynthetic())
                .forEach(field -> getNestedFieldMap(field, root, fieldMap, element));
        return fieldMap;
    }

    /**
     * Gets the nested field map given an object. Note that an object may contain
     * only user defined types or a list. Every field might have several other
     * fields. In order to get it we need to recursively run the function repeatedly
     * until we reach a field for which we are sure there won't be any descendants
     * e.g
     *
     * Class -> Student -> Name. Since name is most likely a string, we won't
     * recurse further Key thing to handle is, when we do have list as a field. We
     * need to handle it specially. If the list is of a primitive type, we add to
     * the map, all its items indexed by the position. <br>
     * If however, the list is made up of a custom user type, we need to recurse for
     * each of the fields in order to populate the map
     *
     *
     * @param field
     *            the field
     * @param root
     *            the root
     * @param fieldMap
     *            the field map
     * @param element
     *            the element
     * @return the nested field map
     */
    @SuppressWarnings("unchecked")
    private void getNestedFieldMap(Field field, String root, Map<String, String> fieldMap, Object element) {
        field.setAccessible(true);
        String keyFieldName = StringUtils.capitalize(field.getName());
        try {
            Object value = field.get(element);
            if (null == value) {
                fieldMap.put(root + "." + keyFieldName, "null");
                return;
            }
            if (value instanceof String || ClassUtils.isPrimitiveOrWrapper(element.getClass())) {
                fieldMap.put(root + "." + keyFieldName, value.toString());
            } else if (value instanceof List) {
                Field listField = element.getClass().getDeclaredField(field.getName());
                listField.setAccessible(true);
                Optional<Class<?>> genericTypeClass = getGenericTypeOfFieldByClassName(element.getClass(),
                        field.getName());

                List<Object> valueItems = (List<Object>) listField.get(element);
                genericTypeClass.filter(ObjectMapperUtil::isClassPrimitiveAndNotUserDefinedType)
                        .ifPresent(c -> IntStream.range(0, valueItems.size()).forEach(i -> {
                            String key = root + "." + keyFieldName + "[" + i + "]";
                            String keyValue = valueItems.get(i).toString();
                            fieldMap.put(key, keyValue);
                        }));
                genericTypeClass.filter(ObjectMapperUtil::isClassNotPrimitiveAndIsUserDefinedType)
                        .ifPresent(c -> IntStream.range(0, valueItems.size()).forEach(i -> {
                            String newRoot = root + "." + keyFieldName + "[" + i + "]";
                            Arrays.stream(c.getDeclaredFields()).filter(f -> !f.isSynthetic())
                                    .forEach(f -> getNestedFieldMap(f, newRoot, fieldMap, valueItems.get(i)));
                        }));

            } else {
                Arrays.stream(field.getType().getDeclaredFields()).filter(f -> !f.isSynthetic())
                        .forEach(f -> getNestedFieldMap(f, root + "." + keyFieldName, fieldMap, value));
            }
        } catch (Exception e) {
            log.error(e.getStackTrace());
        }
    }

    /**
     * Gets the generic type parameter information of a fieldname present in the
     * class. Owing to type erasure, java removes the generics in the underlying
     * byte code. <br>
     * As a result, List<Integer> is simply a List in the class file. This function
     * uses reflection to capture the parameter type information and return it
     * wrapped in an optional. <br>
     * If an exception occurs it returns an empty optional. If no generics are
     * present return the class type of the field
     *
     * @param clazz
     *            the clazz
     * @param fieldName
     *            the field name
     * @return the generic type of field by class name
     */
    public Optional<Class<?>> getGenericTypeOfFieldByClassName(Class<?> clazz, String fieldName) {
        Class<?> fieldType = null;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            fieldType = field.getType();
            ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
            Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
            return Optional.of(stringListClass);
        } catch (NoSuchFieldException e) {
        } catch (ClassCastException e2) {
            return Optional.of(fieldType);
        }
        return Optional.empty();
    }

    /**
     * Checks if is class primitive and not user defined type. Predominantly, if
     * class is a string or any amongst (Boolean, Byte, Character, Short, Integer,
     * Long, Double, Float) or their primitives, it returns true
     *
     * @param clazz
     *            the clazz
     * @return the boolean
     */
    public Boolean isClassPrimitiveAndNotUserDefinedType(Class<?> clazz) {
        return clazz.equals(String.class) || ClassUtils.isPrimitiveOrWrapper(clazz);
    }

    /**
     * Checks if is class not primitive and is user defined type.
     *
     * @param clazz
     *            the clazz
     * @return the boolean
     */
    public Boolean isClassNotPrimitiveAndIsUserDefinedType(Class<?> clazz) {
        return !clazz.equals(String.class) && !ClassUtils.isPrimitiveOrWrapper(clazz);
    }
}
