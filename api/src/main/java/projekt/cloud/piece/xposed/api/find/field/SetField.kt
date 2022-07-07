package projekt.cloud.piece.xposed.api.find.field

import de.robv.android.xposed.XposedHelpers.findField
import de.robv.android.xposed.XposedHelpers.findFieldIfExists

object SetField {

    operator fun <T> FieldWrapper<T>.set(instance: Any, value: T?) =
        findField(clazz, name).set(instance, value)
    
    fun <T> FieldWrapper<T>.setExists(instance: Any, value: T?) =
        findFieldIfExists(clazz, name).set(instance, value)

}