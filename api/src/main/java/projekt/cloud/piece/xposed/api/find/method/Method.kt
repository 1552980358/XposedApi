package projekt.cloud.piece.xposed.api.find.method

import projekt.cloud.piece.xposed.api.find.BaseClassWrapper.Companion.invokeBlock
import projekt.cloud.piece.xposed.api.find.InvokeBlock

object Method {
    
    fun method(method: String) = MethodWrapper().method(method)
    
    fun method(methodBlock: InvokeBlock<MethodWrapper>) =
        MethodWrapper().invokeBlock(methodBlock)
    
    fun method(method: String, methodBlock: InvokeBlock<MethodWrapper> = {}) =
        method(method).invokeBlock(methodBlock)
    
    fun method(method: String, vararg params: Class<*>, methodBlock: InvokeBlock<MethodWrapper> = {}) =
        MethodWrapper().method(method, *params).invokeBlock(methodBlock)
    
    fun method(method: String, vararg params: Any, methodBlock: InvokeBlock<MethodWrapper> = {}) =
        MethodWrapper().method(method, *params).invokeBlock(methodBlock)
    
    fun method(method: String,
               params: Array<Class<*>>,
               vararg paramsObj: Any,
               methodBlock: InvokeBlock<MethodWrapper> = {}) =
        MethodWrapper().method(method, params, *paramsObj).invokeBlock(methodBlock)
    
}