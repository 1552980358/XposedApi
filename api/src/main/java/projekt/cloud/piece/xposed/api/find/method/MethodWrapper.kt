package projekt.cloud.piece.xposed.api.find.method

import de.robv.android.xposed.callbacks.XC_LoadPackage
import projekt.cloud.piece.xposed.api.find.BaseFindMethodWrapper

class MethodWrapper: BaseFindMethodWrapper() {
    
    fun static(clazz: Class<*>) = run {
        this.clazz = clazz
        static()
    }
    fun static(className: String, classLoader: ClassLoader?) = run {
        clazz(className, classLoader)
        static()
    }
    fun static(className: String, loadPackageParam: XC_LoadPackage.LoadPackageParam?) = run {
        clazz(className, loadPackageParam)
        static()
    }
    
    var method: String? = null
    fun method(method: String) = apply {
        this.method = method
    }
    fun method(method: String, vararg params: Class<*>) = apply {
        this.method = method
        params(*params)
    }
    fun method(method: String, vararg params: Any) = apply {
        this.method = method
        params(*params)
    }
    fun method(method: String, params: Array<Class<*>>, vararg paramsObj: Any) = apply {
        this.method = method
        typedParams(params, paramsObj)
    }
    
}