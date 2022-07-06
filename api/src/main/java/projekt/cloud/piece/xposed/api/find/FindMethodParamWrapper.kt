package projekt.cloud.piece.xposed.api.find

import de.robv.android.xposed.XC_MethodHook

class FindMethodParamWrapper(private val hook: MethodHookBlock? = null,
                             private val replace: MethodReplacementBlock? = null) {

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