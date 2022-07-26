package projekt.cloud.piece.xposed.api.resource.string

import android.content.res.Resources
import de.robv.android.xposed.callbacks.XC_InitPackageResources
import projekt.cloud.piece.xposed.api.resource.BaseResourceWrapper
import projekt.cloud.piece.xposed.api.resource.ResourceConstant
import projekt.cloud.piece.xposed.api.resource.ResourceConstant.TYPE_STRING

class StringWrapper: BaseResourceWrapper<String> {

    constructor(packageName: String = ResourceConstant.PACKAGE_ANDROID, resources: Resources = Resources.getSystem()): super(packageName, resources)
    constructor(initPackageResourcesParam: XC_InitPackageResources.InitPackageResourcesParam): super(initPackageResourcesParam)

    override val type: String
        get() = TYPE_STRING

}