package projekt.cloud.piece.xposed.api.method

import de.robv.android.xposed.XposedHelpers.callMethod

class Method(private var methodName: String, private var methodBlock: Method.() -> Unit) {
    
    companion object {
        
        @JvmStatic
        fun method(methodName: String, vararg args: Any?, methodBlock: Method.() -> Unit = {}): Method {
            return Method(methodName, methodBlock).apply {
                if (args.isNotEmpty()) {
                    this.args = arrayOf(*args)
                }
            }
        }
        
        @JvmStatic
        fun method(methodName: String,
                   params: Array<Class<*>>,
                   vararg args: Array<Any?>,
                   methodBlock: Method.() -> Unit = {}) = Method(methodName, methodBlock).apply {
            if (params.size == args.size && params.isNotEmpty()) {
                this.params = params
                args(*args)
            }
        }
        
        @JvmStatic
        fun method(methodName: String, methodBlock: Method.() -> Unit = {}) = Method(methodName, methodBlock)
        
    }
    
    var params: Array<Class<*>>? = null
    fun params(vararg classes: Class<*>) {
        if (classes.isNotEmpty()) {
            params = arrayOf(*classes)
        }
    }
    
    var args: Array<Any?>? = null
    fun args(vararg args: Any?) {
        if (args.isNotEmpty()) {
            this.args = arrayOf(*args)
        }
    }
    
    fun call(instance: Any): Any? {
        methodBlock.invoke(this)
        val params = params
        val args = args
        
        return when {
            !params.isNullOrEmpty() && !args.isNullOrEmpty() -> callMethod(instance, methodName, params, *args)
            !args.isNullOrEmpty() -> callMethod(instance, methodName, *args)
            else -> callMethod(instance, methodName)
        }
    }

}