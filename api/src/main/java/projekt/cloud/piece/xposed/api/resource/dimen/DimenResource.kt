package projekt.cloud.piece.xposed.api.resource.dimen

import projekt.cloud.piece.xposed.api.XposedEntry
import projekt.cloud.piece.xposed.api.base.Block.invokeBlock
import projekt.cloud.piece.xposed.api.resource.DimenBlock

object DimenResource {

    private val XposedEntry.dimenWrapper get() = when (val initPackageResourcesParam = initPackageResourcesParam) {
        null -> DimenWrapper()
        else -> DimenWrapper(initPackageResourcesParam)
    }

    fun XposedEntry.dimen(block: DimenBlock) =
        dimenWrapper.invokeBlock(block)

    fun XposedEntry.dimen(id: Int, block: DimenBlock = {}) =
        dimenWrapper.id(id).invokeBlock(block)

}