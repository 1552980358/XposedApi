package projekt.cloud.piece.xposed.api.find

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import projekt.cloud.piece.xposed.api.find.method.MethodHookParamWrapper

abstract class BaseFindWrapper {
    
    internal companion object {
        fun <T: BaseFindWrapper> T.invokeBlock(block: T.() -> Unit) = apply(block)
    }
    
    var clazz: Class<*>? = null
    fun clazz(className: String, classLoader: ClassLoader?) {
        clazz = XposedHelpers.findClass(className, classLoader)
    }
    fun clazz(className: String, loadPackageParam: XC_LoadPackage.LoadPackageParam?)
        = clazz(className, loadPackageParam?.classLoader)
    
    var params: Array<Class<*>>? = null
    fun params(vararg params: Class<*>) {
        if (params.isNotEmpty()) {
            this.params = arrayOf(*params)
        }
    }
    var paramsObj: Array<Any?>? = null
    fun params(vararg paramsObj: Any?) {
        if (paramsObj.isNotEmpty()) {
            this.paramsObj = arrayOf(*paramsObj)
        }
    }
    
    fun typedParams(params: Array<Class<*>>, vararg paramsObj: Any?) {
        if (params.size == paramsObj.size && params.isNotEmpty()) {
            this.params = params
            this.paramsObj = arrayOf(*paramsObj)
        }
    }
    
    var before: (MethodHookParamWrapper.(XC_MethodHook.MethodHookParam) -> Unit)? = null
    var after: (MethodHookParamWrapper.(XC_MethodHook.MethodHookParam) -> Unit)? = null
    
}