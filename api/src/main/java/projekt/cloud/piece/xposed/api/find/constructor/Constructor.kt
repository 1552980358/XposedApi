package projekt.cloud.piece.xposed.api.find.constructor

import de.robv.android.xposed.callbacks.XC_LoadPackage
import projekt.cloud.piece.xposed.api.find.BaseClassWrapper.Companion.invokeBlock
import projekt.cloud.piece.xposed.api.find.InvokeBlock

object Constructor {

    fun constructor(constructorBlock: InvokeBlock<ConstructorWrapper>) =
            ConstructorWrapper().invokeBlock(constructorBlock)

    fun constructor(clazz: Class<*>, vararg params: Class<*>, constructorBlock: InvokeBlock<ConstructorWrapper> = {}) =
        ConstructorWrapper().clazz(clazz, *params).invokeBlock(constructorBlock)

    fun constructor(className: String,
                    classLoader: ClassLoader?,
                    vararg params: Class<*>,
                    constructorBlock: InvokeBlock<ConstructorWrapper> = {}) =
            ConstructorWrapper().clazz(className, classLoader, *params).invokeBlock(constructorBlock)

    fun constructor(className: String,
                    loadPackageParam: XC_LoadPackage.LoadPackageParam?,
                    vararg params: Class<*>,
                    constructorBlock: InvokeBlock<ConstructorWrapper> = {}) =
        ConstructorWrapper().clazz(className, loadPackageParam, *params).invokeBlock(constructorBlock)

}