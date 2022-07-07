package projekt.cloud.piece.xposed.api.find.field

import de.robv.android.xposed.callbacks.XC_LoadPackage
import projekt.cloud.piece.xposed.api.find.BaseClassWrapper.Companion.invokeBlock
import projekt.cloud.piece.xposed.api.find.InvokeBlock

object Field {
    
    fun <T> field(fieldBlock: InvokeBlock<FieldWrapper<T>>) =
        FieldWrapper<T>().invokeBlock(fieldBlock)
    
    fun <T> field(clazz: Class<*>, name: String, fieldBlock: InvokeBlock<FieldWrapper<T>> = {}) =
        FieldWrapper<T>().field(clazz, name).invokeBlock(fieldBlock)
    
    fun <T> field(className: String,
              classLoader: ClassLoader,
              name: String,
              fieldBlock: InvokeBlock<FieldWrapper<T>> = {}) =
        FieldWrapper<T>().field(className, classLoader, name).invokeBlock(fieldBlock)
    
    fun <T> field(className: String,
              loadPackageParam: XC_LoadPackage.LoadPackageParam,
              name: String,
              fieldBlock: InvokeBlock<FieldWrapper<T>> = {}) =
        FieldWrapper<T>().field(className, loadPackageParam, name).invokeBlock(fieldBlock)
    
}