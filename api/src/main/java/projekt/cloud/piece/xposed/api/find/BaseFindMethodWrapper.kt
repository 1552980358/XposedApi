package projekt.cloud.piece.xposed.api.find

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XC_MethodReplacement

open class BaseFindMethodWrapper: BaseClassWrapper() {

    companion object {

        fun <T: BaseFindMethodWrapper> T.params(vararg params: Class<*>) = apply {
            if (params.isNotEmpty()) {
                this.params = arrayOf(*params)
            }
        }

        fun <T: BaseFindMethodWrapper> T.params(vararg paramsObj: Any?) = apply {
            if (paramsObj.isNotEmpty()) {
                this.paramsObj = arrayOf(*paramsObj)
            }
        }

        fun <T: BaseFindMethodWrapper> T.typedParams(params: Array<Class<*>>, vararg paramsObj: Any?) = apply {
            if (params.size == paramsObj.size && params.isNotEmpty()) {
                this.params = params
                this.paramsObj = arrayOf(*paramsObj)
            }
        }

        fun <T: BaseFindMethodWrapper> T.before(beforeBlock: MethodHookBlock) = apply {
            before = beforeBlock
        }

        fun <T: BaseFindMethodWrapper> T.after(afterBlock: MethodHookBlock) = apply {
            after = afterBlock
        }

        fun <T: BaseFindMethodWrapper> T.replace(replaceBlock: MethodReplacementBlock) = apply {
            replace = replaceBlock
        }

    }

    override val `super`: BaseFindMethodWrapper
        get() = this
    
    var params: Array<Class<*>>? = null

    var paramsObj: Array<Any?>? = null
    
    var before: MethodHookBlock? = null

    var after: MethodHookBlock? = null

    var replace: MethodReplacementBlock? = null

    internal val methodHook: XC_MethodHook
        get() = object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) =
                FindMethodParamWrapper(hook = before).invoke(param)
            override fun afterHookedMethod(param: MethodHookParam) =
                FindMethodParamWrapper(hook = after).invoke(param)
        }

    internal val methodReplacement: XC_MethodReplacement
        get() = object: XC_MethodReplacement() {
            override fun replaceHookedMethod(param: MethodHookParam): Any? =
                FindMethodParamWrapper(replace = replace).invokeResult(param)
        }
    
}