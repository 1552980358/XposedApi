package projekt.cloud.piece.xposed.api.resource

import projekt.cloud.piece.xposed.api.base.InvokeBlock
import projekt.cloud.piece.xposed.api.resource.bool.BooleanWrapper
import projekt.cloud.piece.xposed.api.resource.string.StringWrapper

typealias BooleanBlock = InvokeBlock<BooleanWrapper>

typealias StringBlock = InvokeBlock<StringWrapper>