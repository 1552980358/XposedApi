package projekt.cloud.piece.xposed.api.find.method

import de.robv.android.xposed.XC_MethodHook

class MethodHookParamWrapper(private val hook: (MethodHookParamWrapper.(XC_MethodHook.MethodHookParam) -> Unit)? = null,
                             private val replace: (MethodHookParamWrapper.(XC_MethodHook.MethodHookParam) -> Any?)? = null) {
    
    private lateinit var methodHookParam: XC_MethodHook.MethodHookParam
    internal fun invoke(methodHookParam: XC_MethodHook.MethodHookParam) {
        this.methodHookParam = methodHookParam
        hook?.invoke(this, methodHookParam)
    }

    internal fun invokeResult(methodHookParam: XC_MethodHook.MethodHookParam): Any? {
        this.methodHookParam = methodHookParam
        return replace?.invoke(this, methodHookParam)
    }
    
    fun block() = setResult(null)
    
    fun setResult(result: Any?) {
        methodHookParam.result = result
    }
    
}