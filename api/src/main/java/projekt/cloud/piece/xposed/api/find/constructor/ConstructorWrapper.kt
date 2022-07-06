package projekt.cloud.piece.xposed.api.find.constructor

import de.robv.android.xposed.callbacks.XC_LoadPackage
import projekt.cloud.piece.xposed.api.find.BaseFindWrapper

class ConstructorWrapper: BaseFindWrapper() {

    fun clazz(clazz: Class<*>, vararg params: Class<*>) = apply {
        `super`.clazz(clazz)
        params(*params)
    }

    fun clazz(className: String, classLoader: ClassLoader?, vararg params: Class<*>) = apply {
        `super`.clazz(className, classLoader)
        params(*params)
    }

    fun clazz(className: String,
              loadPackageParam: XC_LoadPackage.LoadPackageParam?,
              vararg params: Class<*>) = apply {
        `super`.clazz(className, loadPackageParam)
        params(*params)
    }

}