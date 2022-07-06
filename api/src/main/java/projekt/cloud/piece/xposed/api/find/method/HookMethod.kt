package projekt.cloud.piece.xposed.api.find.method

import de.robv.android.xposed.XposedHelpers.findAndHookMethod

object HookMethod {
    
    fun MethodWrapper.hook() = when {
        clazz == null || method == null -> null
        else -> when (val params = paramsObj) {
            null -> findAndHookMethod(clazz, method, methodHook)
            else -> findAndHookMethod(clazz, method, *params, methodHook)
        }
    }

}