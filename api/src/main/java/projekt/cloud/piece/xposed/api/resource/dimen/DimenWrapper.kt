package projekt.cloud.piece.xposed.api.resource.dimen

import android.content.res.Resources
import android.content.res.XResources.DimensionReplacement
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.COMPLEX_UNIT_PX
import android.util.TypedValue.COMPLEX_UNIT_SP
import de.robv.android.xposed.callbacks.XC_InitPackageResources
import projekt.cloud.piece.xposed.api.resource.BaseResourceWrapper
import projekt.cloud.piece.xposed.api.resource.BaseResourceWrapper.Companion.id
import projekt.cloud.piece.xposed.api.resource.ReplaceResource.replace
import projekt.cloud.piece.xposed.api.resource.ResourceConstant
import projekt.cloud.piece.xposed.api.resource.ResourceConstant.TYPE_DIMEN

class DimenWrapper(packageName: String = ResourceConstant.PACKAGE_ANDROID, resources: Resources = Resources.getSystem()) {

    companion object {

        const val DP = COMPLEX_UNIT_DIP
        const val PX = COMPLEX_UNIT_PX
        const val SP = COMPLEX_UNIT_SP

    }

    constructor(initPackageResourcesParam: XC_InitPackageResources.InitPackageResourcesParam):
            this(initPackageResourcesParam.packageName, initPackageResourcesParam.res)

    private class DimenWrapperImpl(packageName: String = ResourceConstant.PACKAGE_ANDROID, resources: Resources = Resources.getSystem()):
        BaseResourceWrapper<DimensionReplacement>(packageName, resources) {

        override val type: String
            get() = TYPE_DIMEN

    }

    private val dimenWrapperImpl = DimenWrapperImpl(packageName, resources)

    fun replace(value: Float, unit: Int) =
        dimenWrapperImpl.replace(DimensionReplacement(value, unit))

    fun id(resId: Int) = apply {
        dimenWrapperImpl.id(resId)
    }

    fun id(name: String) = apply {
        dimenWrapperImpl.id(name)
    }

}