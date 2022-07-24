package projekt.cloud.piece.xposed.api.find.method

import projekt.cloud.piece.xposed.api.base.Block.invokeBlock
import projekt.cloud.piece.xposed.api.find.MethodBlock

object Method {
    
    fun method(method: String) = MethodWrapper().method(method)
    
    fun method(methodBlock: MethodBlock) =
        MethodWrapper().invokeBlock(methodBlock)
    
    fun method(method: String, methodBlock: MethodBlock = {}) =
        method(method).invokeBlock(methodBlock)
    
    fun method(method: String, vararg params: Class<*>, methodBlock: MethodBlock = {}) =
        MethodWrapper().method(method, *params).invokeBlock(methodBlock)
    
    fun method(method: String, vararg params: Any, methodBlock: MethodBlock = {}) =
        MethodWrapper().method(method, *params).invokeBlock(methodBlock)
    
    fun method(method: String,
               params: Array<Class<*>>,
               vararg paramsObj: Any,
               methodBlock: MethodBlock = {}) =
        MethodWrapper().method(method, params, *paramsObj).invokeBlock(methodBlock)
    
}