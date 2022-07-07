package projekt.cloud.piece.xposed.api.find

import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

open class BaseClassWrapper {

    companion object {

        internal fun <T: BaseClassWrapper> T.invokeBlock(block: InvokeBlock<T>) = apply(block)

        fun <T: BaseFindMethodWrapper> T.clazz(clazz: Class<*>) = apply {
            this.clazz = clazz
        }

        fun <T: BaseFindMethodWrapper> T.clazz(className: String, classLoader: ClassLoader?) =
                clazz(XposedHelpers.findClass(className, classLoader))

        fun <T: BaseFindMethodWrapper> T.clazz(className: String, loadPackageParam: XC_LoadPackage.LoadPackageParam?) =
                clazz(className, loadPackageParam?.classLoader)

    }

    var clazz: Class<*>? = null

    internal open val `super`: BaseClassWrapper
        get() = this

}