package projekt.cloud.piece.xposed.api.method

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XposedHelpers.findClass
import de.robv.android.xposed.callbacks.XC_LoadPackage

class HookMethod(methodHookBlock: HookMethod.() -> Unit) {
    
    var clazz: Class<*>? = null
    fun clazz(className: String, classLoader: ClassLoader?) {
        clazz = findClass(className, classLoader)
    }
    fun clazz(className: String, loadPackageParam: XC_LoadPackage.LoadPackageParam?) {
        clazz(className, loadPackageParam?.classLoader)
    }
    
    var method: String? = null
    fun method(methodName: String, vararg params: Class<*>) {
        this.method = methodName
        if (params.isNotEmpty()) {
            this.params = arrayOf(*params)
        }
    }
    
    var params: Array<Class<*>>? = null

    private var _before: ((XC_MethodHook.MethodHookParam) -> Unit)? = null
    fun before(beforeBlock: ((methodHookParam: XC_MethodHook.MethodHookParam) -> Unit)) {
        _before = beforeBlock
    }
    
    private var _after: ((XC_MethodHook.MethodHookParam) -> Unit)? = null
    fun after(afterBlock: ((methodHookParam: XC_MethodHook.MethodHookParam) -> Unit)) {
        _after = afterBlock
    }
    
    init {
        methodHookBlock.invoke(this)
    }
    
    fun wrapAndImplement(): XC_MethodHook.Unhook? {
        if (clazz != null && !method.isNullOrBlank()) {
            when (val params = params) {
                null -> findAndHookMethod(clazz, method, object: XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        _before?.invoke(param)
                    }
                    override fun afterHookedMethod(param: MethodHookParam) {
                        _after?.invoke(param)
                    }
                })
                else -> findAndHookMethod(clazz, method, *params, object: XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        _before?.invoke(param)
                    }
                    override fun afterHookedMethod(param: MethodHookParam) {
                        _after?.invoke(param)
                    }
                })
            }
        }
        return null
    }

}