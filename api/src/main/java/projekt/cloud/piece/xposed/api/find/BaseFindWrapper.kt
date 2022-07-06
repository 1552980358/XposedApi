package projekt.cloud.piece.xposed.api.find

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import projekt.cloud.piece.xposed.api.find.method.MethodHookParamWrapper

abstract class BaseFindWrapper {
    
    internal companion object {

        fun <T: BaseFindWrapper> T.invokeBlock(block: T.() -> Unit) = apply(block)

        fun <T: BaseFindWrapper> T.clazz(className: String, classLoader: ClassLoader?) = apply {
            clazz = XposedHelpers.findClass(className, classLoader)
        }

        fun <T: BaseFindWrapper> T.clazz(className: String, loadPackageParam: XC_LoadPackage.LoadPackageParam?) = apply {
            clazz = XposedHelpers.findClass(className, loadPackageParam?.classLoader)
        }

        fun <T: BaseFindWrapper> T.params(vararg params: Class<*>) = apply {
            if (params.isNotEmpty()) {
                this.params = arrayOf(*params)
            }
        }

        fun <T: BaseFindWrapper> T.params(vararg paramsObj: Any?) = apply {
            if (paramsObj.isNotEmpty()) {
                this.paramsObj = arrayOf(*paramsObj)
            }
        }

        fun <T: BaseFindWrapper> T.typedParams(params: Array<Class<*>>, vararg paramsObj: Any?) = apply {
            if (params.size == paramsObj.size && params.isNotEmpty()) {
                this.params = params
                this.paramsObj = arrayOf(*paramsObj)
            }
        }

        fun <T: BaseFindWrapper> T.before(beforeBlock: MethodHookParamWrapper.(XC_MethodHook.MethodHookParam) -> Unit) = apply {
            before = beforeBlock
        }

        fun <T: BaseFindWrapper> T.after(afterBlock: MethodHookParamWrapper.(XC_MethodHook.MethodHookParam) -> Unit) = apply {
            after = afterBlock
        }

    }
    
    var clazz: Class<*>? = null
    
    var params: Array<Class<*>>? = null

    var paramsObj: Array<Any?>? = null
    
    var before: (MethodHookParamWrapper.(XC_MethodHook.MethodHookParam) -> Unit)? = null

    var after: (MethodHookParamWrapper.(XC_MethodHook.MethodHookParam) -> Unit)? = null
    
}