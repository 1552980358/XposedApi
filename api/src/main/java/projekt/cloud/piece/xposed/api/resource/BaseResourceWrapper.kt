package projekt.cloud.piece.xposed.api.resource

import android.content.res.Resources
import android.content.res.XResources
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam
import projekt.cloud.piece.xposed.api.resource.ResourceConstant.PACKAGE_ANDROID

abstract class BaseResourceWrapper<T>(protected val packageName: String = PACKAGE_ANDROID, val resources: Resources = Resources.getSystem()) {

    constructor(initPackageResourcesParam: InitPackageResourcesParam):
        this(initPackageResourcesParam.packageName, initPackageResourcesParam.res)

    constructor(packageName: String, xResources: XResources): this(packageName, xResources as Resources) {
        isXResources = true
    }

    companion object {

        fun <T, B: BaseResourceWrapper<T>> B.id(id: Int) = apply {
            this.id = id
        }

        fun <T, B: BaseResourceWrapper<T>> B.id(name: String) = apply {
            id = resources.getIdentifier(name, type, packageName)
        }

    }

    internal var isXResources = false

    var id = 0

    protected abstract val type: String

    internal val xResources: XResources
        get() = resources as XResources

}