package projekt.cloud.piece.xposed.api

import android.app.Activity
import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.robv.android.xposed.callbacks.XC_LoadPackage
import org.junit.Test
import org.junit.runner.RunWith
import projekt.cloud.piece.xposed.api.find.method.CallMethod.call
import projekt.cloud.piece.xposed.api.find.method.HookMethod.hook
import projekt.cloud.piece.xposed.api.find.method.Method.method

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    
    @Test
    fun callActivityFinish(activity: Activity) {
        // A quick finish() call with xposed callMethod()
        method("finish").call(activity)
        // DSL-styled
        method {
            method = "finish"
            call(activity)  // Ignore result
        }// .call(activity) to obtain result
    }
    
    @Test
    fun hookActivityOnCreate(classLoader: ClassLoader?, loadPackageParam: XC_LoadPackage.LoadPackageParam?) {
        // DSL-styled
        method {
            // With Class accessible directly
            clazz = Activity::class.java
            // With string Class path
            // Both ClassLoader and LoadPackageParam acceptable
            clazz("android.app.Activity", classLoader)
            clazz("android.app.Activity", loadPackageParam)
            // Same as `method("onCreate", Bundle::class.java)`
            method = "onCreate"
            // Same as `params = arrayOf(Bundle::class.java)`
            params(Bundle::class.java)
            before {
                // Before onCreate called
            }
            after {
                // After onCreate called
            }
            hook()  // Ignore result
        }.hook()    // Obtain result returned by findAndHookMethod()
        
        // Builder-like style
        method("onCreate", Bundle::class.java)
            .before {
                // Before onCreate called
            }
            .after {
                // After onCreate called
            }
            .hook()
    }
    
}