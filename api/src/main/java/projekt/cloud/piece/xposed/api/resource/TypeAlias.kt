package projekt.cloud.piece.xposed.api.resource

import projekt.cloud.piece.xposed.api.base.InvokeBlock
import projekt.cloud.piece.xposed.api.resource.bool.BooleanWrapper
import projekt.cloud.piece.xposed.api.resource.dimen.DimenWrapper
import projekt.cloud.piece.xposed.api.resource.string.StringWrapper

typealias BooleanBlock = InvokeBlock<BooleanWrapper>

typealias DimenBlock = InvokeBlock<DimenWrapper>

typealias StringBlock = InvokeBlock<StringWrapper>