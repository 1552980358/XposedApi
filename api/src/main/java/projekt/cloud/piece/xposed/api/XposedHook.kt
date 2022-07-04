package projekt.cloud.piece.xposed.api

import projekt.cloud.piece.xposed.api.method.HookMethod

object XposedHook {

    @JvmStatic
    fun hookMethod(hookMethodBlock: HookMethod.() -> Unit) =
        HookMethod(hookMethodBlock).wrapAndImplement()
    
}