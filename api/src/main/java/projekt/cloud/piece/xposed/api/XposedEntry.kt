package projekt.cloud.piece.xposed.api

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
    
    override fun initZygote(startupParam: StartupParam) {
        onZygote(startupParam)
    }
    
    override fun handleLoadPackage(loadPackageParam: LoadPackageParam) {
        onLoadPackage(loadPackageParam)
    }
    
    override fun handleInitPackageResources(initPackageResourcesParam: InitPackageResourcesParam) {
        onLoadResources(initPackageResourcesParam)
    }
    
}