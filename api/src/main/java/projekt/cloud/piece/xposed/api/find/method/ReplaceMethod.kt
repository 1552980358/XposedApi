package projekt.cloud.piece.xposed.api.find.method

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers

object ReplaceMethod {

    fun MethodWrapper.replace() = when {
        clazz == null || method == null -> null
        else -> {
            val methodReplacement = object: XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any? =
                    MethodHookParamWrapper(replace = replace).invokeResult(param)
            }
            when (val params = paramsObj) {
                null -> XposedHelpers.findAndHookMethod(clazz, method, methodReplacement)
                else -> XposedHelpers.findAndHookMethod(clazz, method, *params, methodReplacement)
            }
        }
    }

}