package projekt.cloud.piece.xposed.api

import android.app.Activity
import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import projekt.cloud.piece.xposed.api.method.Method.Companion.method

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    
    @Test
    fun hookActivityOnCreate() {
        XposedHook.hookMethod {
            clazz = Activity::class.java
            /**
             * Same as `method("onCreate", Bundle::class.java)`
             **/
            method = "onCreate"
            params = arrayOf(Bundle::class.java)
            before {
                // Before onCreate() called
            }
            after {
                // After onCreate() called
            }
        }
    }
    
    @Test
    fun callActivityFinish(activity: Activity) {
        method("finish").call(activity)
    }
    
}