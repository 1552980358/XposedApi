package projekt.cloud.piece.xposed.api.resource.bool

import projekt.cloud.piece.xposed.api.XposedEntry
import projekt.cloud.piece.xposed.api.base.Block.invokeBlock
import projekt.cloud.piece.xposed.api.resource.BaseResourceWrapper.Companion.id
import projekt.cloud.piece.xposed.api.resource.BooleanBlock

object BooleanResource {

    private val XposedEntry.booleanWrapper get() = when (val initPackageResourcesParam = initPackageResourcesParam) {
        null -> BooleanWrapper()
        else -> BooleanWrapper(initPackageResourcesParam)
    }

    fun XposedEntry.boolean(block: BooleanBlock) =
        booleanWrapper.invokeBlock(block)

    fun XposedEntry.boolean(id: Int, block: BooleanBlock = {}) =
        booleanWrapper.id(id).invokeBlock(block)

}