package projekt.cloud.piece.xposed.api.resource.bool

import android.content.res.Resources
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam
import projekt.cloud.piece.xposed.api.resource.BaseResourceWrapper
import projekt.cloud.piece.xposed.api.resource.ResourceConstant.PACKAGE_ANDROID
import projekt.cloud.piece.xposed.api.resource.ResourceConstant.TYPE_BOOLEAN

class BooleanWrapper: BaseResourceWrapper<Boolean> {

    constructor(packageName: String = PACKAGE_ANDROID, resources: Resources = Resources.getSystem()): super(packageName, resources)
    constructor(initPackageResourcesParam: InitPackageResourcesParam): super(initPackageResourcesParam)

    override val type: String
        get() = TYPE_BOOLEAN

}