package projekt.cloud.piece.xposed.api.find.field

import de.robv.android.xposed.callbacks.XC_LoadPackage
import projekt.cloud.piece.xposed.api.find.BaseClassWrapper

class FieldWrapper<T>: BaseClassWrapper() {

    var name: String? = null
        set(value) {
            if (!value.isNullOrBlank()) {
                field = value
            }
        }

    fun field(clazz: Class<*>, name: String) = apply {
        clazz(clazz)
        this.name = name
    }
    
    fun field(className: String, classLoader: ClassLoader?, name: String) = apply {
        clazz(className, classLoader)
        this.name = name
    }
    
    fun field(className: String,
              loadPackageParam: XC_LoadPackage.LoadPackageParam,
              name: String) = apply {
        clazz(className, loadPackageParam)
        this.name = name
    }

}