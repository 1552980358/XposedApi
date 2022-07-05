package projekt.cloud.piece.xposed.api.find.method

import projekt.cloud.piece.xposed.api.find.BaseFindWrapper.Companion.invokeBlock

object Method {
    
    fun method(method: String) = MethodWrapper().method(method)
    
    fun method(methodBlock: MethodWrapper.() -> Unit) =
        MethodWrapper().invokeBlock(methodBlock)
    
    fun method(method: String, methodBlock: MethodWrapper.() -> Unit = {}) =
        method(method).invokeBlock(methodBlock)
    
    fun method(method: String, vararg params: Class<*>, methodBlock: MethodWrapper.() -> Unit = {}) =
        MethodWrapper().method(method, *params).invokeBlock(methodBlock)
    
    fun method(method: String, vararg params: Any, methodBlock: MethodWrapper.() -> Unit = {}) =
        MethodWrapper().method(method, *params).invokeBlock(methodBlock)
    
    fun method(method: String,
               params: Array<Class<*>>,
               vararg paramsObj: Any,
               methodBlock: MethodWrapper.() -> Unit = {}) =
        MethodWrapper().method(method, params, *paramsObj).invokeBlock(methodBlock)
    
}