package projekt.cloud.piece.xposed.api

import android.content.res.XModuleResources
import de.robv.android.xposed.IXposedHookInitPackageResources
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.IXposedHookZygoteInit.StartupParam
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

open class XposedEntry: IXposedHookZygoteInit,
    IXposedHookLoadPackage,
    IXposedHookInitPackageResources {
    
    protected open fun onZygote(startupParam: StartupParam) = Unit
    
    protected open fun onLoadPackage(loadPackageParam: LoadPackageParam) = Unit
    
    protected open fun onLoadResources(initPackageResourcesParam: InitPackageResourcesParam)
        = Unit

    private var _packageName: String? = null
    val packageName: String
        get() = _packageName!!

    var initPackageResourcesParam: InitPackageResourcesParam? = null

    private var _moduleResources: XModuleResources? = null
    protected val moduleResources: XModuleResources
        get() = _moduleResources!!

    private var _modulePath: String? = null
    private val modulePath: String
        get() = _modulePath!!
    
    override fun initZygote(startupParam: StartupParam) {
        _modulePath = startupParam.modulePath
        onZygote(startupParam)
    }
    
    override fun handleLoadPackage(loadPackageParam: LoadPackageParam) {
        _packageName = loadPackageParam.packageName
        onLoadPackage(loadPackageParam)
    }
    
    override fun handleInitPackageResources(initPackageResourcesParam: InitPackageResourcesParam) {
        this.initPackageResourcesParam = initPackageResourcesParam
        _moduleResources = XModuleResources.createInstance(modulePath, initPackageResourcesParam.res)
        onLoadResources(initPackageResourcesParam)

        // Recycle
        _modulePath = null
        _packageName = null
        this.initPackageResourcesParam = null
    }
    
}