package projekt.cloud.piece.xposed.api.find

import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

open class BaseClassWrapper {

    companion object {

        internal fun <T: BaseClassWrapper> T.invokeBlock(block: InvokeBlock<T>) = apply(block)

        fun <T: BaseClassWrapper> T.clazz(clazz: Class<*>) = apply {
            this.clazz = clazz
        }

        fun <T: BaseClassWrapper> T.clazz(className: String, classLoader: ClassLoader?) =
                clazz(XposedHelpers.findClass(className, classLoader))

        fun <T: BaseClassWrapper> T.clazz(className: String, loadPackageParam: XC_LoadPackage.LoadPackageParam?) =
                clazz(className, loadPackageParam?.classLoader)
    
        fun <T: BaseClassWrapper> T.static() = apply {
            static = true
        }

    }

    var clazz: Class<*>? = null

    internal open val `super`: BaseClassWrapper
        get() = this
    
    var static = false

}