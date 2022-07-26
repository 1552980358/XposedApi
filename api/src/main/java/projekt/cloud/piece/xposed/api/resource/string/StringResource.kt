package projekt.cloud.piece.xposed.api.resource.string

import projekt.cloud.piece.xposed.api.XposedEntry
import projekt.cloud.piece.xposed.api.base.Block.invokeBlock
import projekt.cloud.piece.xposed.api.resource.BaseResourceWrapper.Companion.id
import projekt.cloud.piece.xposed.api.resource.StringBlock

object StringResource {

    private val XposedEntry.stringWrapper get() = when (val initPackageResourcesParam = initPackageResourcesParam) {
        null -> StringWrapper()
        else -> StringWrapper(initPackageResourcesParam)
    }

    fun XposedEntry.string(block: StringBlock) =
        stringWrapper.invokeBlock(block)

    fun XposedEntry.string(id: Int, block: StringBlock = {}) =
        stringWrapper.id(id).invokeBlock(block)

}