package projekt.cloud.piece.xposed.api.find.field

import de.robv.android.xposed.XposedHelpers.findField
import de.robv.android.xposed.XposedHelpers.findFieldIfExists

object GetField {

    @Suppress("UNCHECKED_CAST")
    operator fun <T> FieldWrapper<T>.get(instance: Any) =
        findField(clazz, name).get(instance) as T
    
    @Suppress("UNCHECKED_CAST")
    fun <T> FieldWrapper<T>.getExists(instance: Any) =
        findFieldIfExists(clazz, name)?.get(instance) as? T
    
}