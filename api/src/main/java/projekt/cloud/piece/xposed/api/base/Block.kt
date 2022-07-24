package projekt.cloud.piece.xposed.api.base

typealias InvokeBlock<T> = T.() -> Unit

object Block {

    internal fun <T> T.invokeBlock(block: InvokeBlock<T>) = apply(block)

}