package projekt.cloud.piece.xposed.api.find.field

import de.robv.android.xposed.XposedHelpers.findField
import de.robv.android.xposed.XposedHelpers.findFieldIfExists

object SetField {

    operator fun <T> FieldWrapper<T>.set(instance: Any, value: T?) =
        setImpl(instance, value)
    
    fun <T> FieldWrapper<T>.set(value: T?) =
        setImpl(value = value)
    
    fun <T> FieldWrapper<T>.setExists(instance: Any, value: T?) =
        setExistsImpl(instance, value)
    
    fun <T> FieldWrapper<T>.setExists(value: T?) =
        setExistsImpl(value = value)
    
    private fun <T> FieldWrapper<T>.setImpl(instance: Any? = null, value: T?) =
        findField(clazz, name).set(instance, value)
    
    private fun <T> FieldWrapper<T>.setExistsImpl(instance: Any? = null, value: T?) =
        findFieldIfExists(clazz, name).set(instance, value)

}