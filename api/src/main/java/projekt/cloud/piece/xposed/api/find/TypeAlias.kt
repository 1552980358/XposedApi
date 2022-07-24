package projekt.cloud.piece.xposed.api.find

import de.robv.android.xposed.XC_MethodHook
import projekt.cloud.piece.xposed.api.base.InvokeBlock
import projekt.cloud.piece.xposed.api.find.constructor.ConstructorWrapper
import projekt.cloud.piece.xposed.api.find.field.FieldWrapper
import projekt.cloud.piece.xposed.api.find.method.MethodWrapper

typealias ConstructorBlock = InvokeBlock<ConstructorWrapper>

typealias MethodBlock = InvokeBlock<MethodWrapper>

typealias FieldBlock<T> = InvokeBlock<FieldWrapper<T>>

typealias MethodHookBlock = FindMethodParamWrapper.(XC_MethodHook.MethodHookParam) -> Unit

typealias MethodReplacementBlock = FindMethodParamWrapper.(XC_MethodHook.MethodHookParam) -> Any?