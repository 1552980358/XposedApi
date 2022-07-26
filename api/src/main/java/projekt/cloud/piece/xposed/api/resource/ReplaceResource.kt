package projekt.cloud.piece.xposed.api.resource

import android.content.res.XResources

object ReplaceResource {

    fun <T> BaseResourceWrapper<T>.replace(newValue: T) = when {
        isXResources -> replaceXResource(newValue)
        else -> replaceSystem(newValue)
    }

    private fun <T> BaseResourceWrapper<T>.replaceXResource(newValue: T) =
        xResources.setReplacement(id, newValue)

    private fun <T> BaseResourceWrapper<T>.replaceSystem(newValue: T) =
        XResources.setSystemWideReplacement(id, newValue)

}