package projekt.cloud.piece.xposed.api.find.constructor

import de.robv.android.xposed.XposedHelpers.findAndHookConstructor

object HookConstructor {

    fun ConstructorWrapper.hook() = when {
        clazz == null -> null
        else -> when (val params = params) {
            null -> findAndHookConstructor(clazz, methodHook)
            else -> findAndHookConstructor(clazz, params, methodHook)
        }
    }

}