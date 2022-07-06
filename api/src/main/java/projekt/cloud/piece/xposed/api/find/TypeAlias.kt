package projekt.cloud.piece.xposed.api.find

import de.robv.android.xposed.XC_MethodHook

typealias InvokeBlock<T> = T.() -> Unit

typealias MethodHookBlock = FindMethodParamWrapper.(XC_MethodHook.MethodHookParam) -> Unit

typealias MethodReplacementBlock = FindMethodParamWrapper.(XC_MethodHook.MethodHookParam) -> Any?