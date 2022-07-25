package projekt.cloud.piece.xposed.api.resource.bool

import android.content.res.XResources

object ReplaceBoolean {

    fun BooleanWrapper.replace(newValue: Boolean) = when {
        isXResources -> replaceXResource(newValue)
        else -> replaceSystem(newValue)
    }

    private fun BooleanWrapper.replaceXResource(newValue: Boolean) =
        xResources.setReplacement(id, newValue)

    private fun BooleanWrapper.replaceSystem(newValue: Boolean) =
        XResources.setSystemWideReplacement(id, newValue)

}