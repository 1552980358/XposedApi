package projekt.cloud.piece.xposed.api.find.method

import de.robv.android.xposed.XposedHelpers.callMethod
import de.robv.android.xposed.XposedHelpers.callStaticMethod

object CallMethod {
    
    fun MethodWrapper.call(instance: Any? = null) = when {
        static -> callStaticImpl()
        else -> instance?.let { callImpl(it) }
    }
    fun MethodWrapper.callStatic() = callStaticImpl()
    
    private fun MethodWrapper.callImpl(instance: Any) = when {
        method == null -> null
        paramsObj.isNullOrEmpty() -> callMethod(instance, method)
        params.isNullOrEmpty() -> callMethod(instance, method, *paramsObj!!)
        else -> callMethod(instance, method, params, *paramsObj!!)
    }
    
    private fun MethodWrapper.callStaticImpl() = when {
        clazz == null || method == null -> null
        paramsObj.isNullOrEmpty() -> callStaticMethod(clazz, method)
        params.isNullOrEmpty() -> callStaticMethod(clazz, method, *paramsObj!!)
        else -> callStaticMethod(clazz, method, params, *paramsObj!!)
    }
    
}