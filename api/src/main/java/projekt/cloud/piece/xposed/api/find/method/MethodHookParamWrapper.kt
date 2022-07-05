package projekt.cloud.piece.xposed.api.find.method

import de.robv.android.xposed.XC_MethodHook

class MethodHookParamWrapper(private val block: (MethodHookParamWrapper.(XC_MethodHook.MethodHookParam) -> Unit)?) {
    
    private lateinit var methodHookParam: XC_MethodHook.MethodHookParam
    internal fun invoke(methodHookParam: XC_MethodHook.MethodHookParam) {
        this.methodHookParam = methodHookParam
        block?.invoke(this, methodHookParam)
    }
    
    fun block() = setResult(null)
    
    fun setResult(result: Any?) {
        methodHookParam.result = result
    }
    
}