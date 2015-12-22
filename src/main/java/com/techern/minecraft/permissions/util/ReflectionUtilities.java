package com.techern.minecraft.permissions.util;

import java.lang.reflect.Field;

/**
 * A class used to help with Java's reflection abilities
 *
 * @since 0.0.1
 */
public class ReflectionUtilities {

    /**
     * Gets a {@link Field} in a {@link Class}'s hierarchy
     *
     * @param classBeingSearched The {@link Class} being searched
     * @param deobfuscatedFieldName The {@link Field}'s name when in a deobfuscated / development environment
     * @param obfuscatedFieldName The {@link Field}'s name when in an obfuscated environment
     * @return A {@link Field} if successful
     *
     * @throws NoSuchFieldException If not field in the hierarchy
     * @since 0.0.1
     */
    public static Field getFieldInHierarchy(Class<?> classBeingSearched, String deobfuscatedFieldName, String obfuscatedFieldName) throws NoSuchFieldException {
        try {
            try {
                return classBeingSearched.getDeclaredField(deobfuscatedFieldName);
            } catch (NoSuchFieldException exception) {
                return classBeingSearched.getDeclaredField(obfuscatedFieldName);
            }
        } catch (NoSuchFieldException exception) {
            Class<?> superClass = classBeingSearched.getSuperclass();

            //Check to see if it exists
            if (superClass != null) {
                return getFieldInHierarchy(superClass, deobfuscatedFieldName, obfuscatedFieldName);
            } else {
                throw new NoSuchFieldException("Not even in hierarchy: \"" + deobfuscatedFieldName + " | " + obfuscatedFieldName + "\"");
            }
        }
    }

}
