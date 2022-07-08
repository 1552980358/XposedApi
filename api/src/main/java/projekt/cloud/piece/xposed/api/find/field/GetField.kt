package projekt.cloud.piece.xposed.api.find.field

import de.robv.android.xposed.XposedHelpers.findField
import de.robv.android.xposed.XposedHelpers.findFieldIfExists

object GetField {
    
    operator fun <T> FieldWrapper<T>.get(instance: Any) = getImpl(instance)
    
    fun <T> FieldWrapper<T>.get() = getImpl()
    
    fun <T> FieldWrapper<T>.getExists(instance: Any) = getExistsImpl(instance)
    
    fun <T> FieldWrapper<T>.getExists() = getExistsImpl()
    
    @Suppress("UNCHECKED_CAST")
    private fun <T> FieldWrapper<T>.getImpl(instance: Any? = null) =
        findField(clazz, name).get(instance) as T
    
    @Suppress("UNCHECKED_CAST")
    private fun <T> FieldWrapper<T>.getExistsImpl(instance: Any? = null) =
        findFieldIfExists(clazz, name)?.get(instance) as? T
    
}