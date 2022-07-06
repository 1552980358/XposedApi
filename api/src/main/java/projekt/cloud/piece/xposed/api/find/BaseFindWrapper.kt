package projekt.cloud.piece.xposed.api.find

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

abstract class BaseFindWrapper {

    companion object {

        internal fun <T: BaseFindWrapper> T.invokeBlock(block: T.() -> Unit) = apply(block)

        fun <T: BaseFindWrapper> T.clazz(clazz: Class<*>) = apply {
            this.clazz = clazz
        }

        fun <T: BaseFindWrapper> T.clazz(className: String, classLoader: ClassLoader?) =
            clazz(XposedHelpers.findClass(className, classLoader))

        fun <T: BaseFindWrapper> T.clazz(className: String, loadPackageParam: XC_LoadPackage.LoadPackageParam?) =
            clazz(className, loadPackageParam?.classLoader)

        fun <T: BaseFindWrapper> T.params(vararg params: Class<*>) = apply {
            if (params.isNotEmpty()) {
                this.params = arrayOf(*params)
            }
        }

        fun <T: BaseFindWrapper> T.params(vararg paramsObj: Any?) = apply {
            if (paramsObj.isNotEmpty()) {
                this.paramsObj = arrayOf(*paramsObj)
            }
        }

        fun <T: BaseFindWrapper> T.typedParams(params: Array<Class<*>>, vararg paramsObj: Any?) = apply {
            if (params.size == paramsObj.size && params.isNotEmpty()) {
                this.params = params
                this.paramsObj = arrayOf(*paramsObj)
            }
        }

        fun <T: BaseFindWrapper> T.before(beforeBlock: MethodHookBlock) = apply {
            before = beforeBlock
        }

        fun <T: BaseFindWrapper> T.after(afterBlock: MethodHookBlock) = apply {
            after = afterBlock
        }

        fun <T: BaseFindWrapper> T.replace(replaceBlock: MethodReplacementBlock) = apply {
            replace = replaceBlock
        }

    }

    internal val `super`: BaseFindWrapper
        get() = this
    
    var clazz: Class<*>? = null
    
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