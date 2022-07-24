package projekt.cloud.piece.xposed.api.find.field

import de.robv.android.xposed.callbacks.XC_LoadPackage
import projekt.cloud.piece.xposed.api.base.Block.invokeBlock
import projekt.cloud.piece.xposed.api.find.FieldBlock

object Field {
    
    fun <T> field(fieldBlock: FieldBlock<T>) =
        FieldWrapper<T>().invokeBlock(fieldBlock)
    
    fun <T> field(clazz: Class<*>, name: String, fieldBlock: FieldBlock<T> = {}) =
        FieldWrapper<T>().field(clazz, name).invokeBlock(fieldBlock)
    
    fun <T> field(className: String,
              classLoader: ClassLoader,
              name: String,
              fieldBlock: FieldBlock<T> = {}) =
        FieldWrapper<T>().field(className, classLoader, name).invokeBlock(fieldBlock)
    
    fun <T> field(className: String,
              loadPackageParam: XC_LoadPackage.LoadPackageParam,
              name: String,
              fieldBlock: FieldBlock<T> = {}) =
        FieldWrapper<T>().field(className, loadPackageParam, name).invokeBlock(fieldBlock)
    
}