package projekt.cloud.piece.xposed.api.find.method

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers.findAndHookMethod

object HookMethod {
    
    fun MethodWrapper.hook() = when {
        clazz == null || method == null -> null
        else -> {
            val methodHook = object: XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    MethodHookParamWrapper(before).invoke(param)
                }
                override fun afterHookedMethod(param: MethodHookParam) {
                    MethodHookParamWrapper(after).invoke(param)
                }
            }
            when (val params = paramsObj) {
                null -> findAndHookMethod(clazz, method, methodHook)
                else -> findAndHookMethod(clazz, method, *params, methodHook)
            }
        }
    }

}