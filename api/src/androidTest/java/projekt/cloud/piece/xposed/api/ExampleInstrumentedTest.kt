package projekt.cloud.piece.xposed.api

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.autofill.AutofillManager
import androidx.annotation.StyleRes
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.robv.android.xposed.callbacks.XC_LoadPackage
import org.junit.Test
import org.junit.runner.RunWith
import projekt.cloud.piece.xposed.api.find.BaseClassWrapper.Companion.clazz
import projekt.cloud.piece.xposed.api.find.BaseClassWrapper.Companion.static
import projekt.cloud.piece.xposed.api.find.BaseFindMethodWrapper.Companion.after
import projekt.cloud.piece.xposed.api.find.BaseFindMethodWrapper.Companion.before
import projekt.cloud.piece.xposed.api.find.BaseFindMethodWrapper.Companion.params
import projekt.cloud.piece.xposed.api.find.constructor.Constructor.constructor
import projekt.cloud.piece.xposed.api.find.constructor.HookConstructor.hook
import projekt.cloud.piece.xposed.api.find.field.Field.field
import projekt.cloud.piece.xposed.api.find.field.GetField.get
import projekt.cloud.piece.xposed.api.find.field.GetField.getExists
import projekt.cloud.piece.xposed.api.find.field.SetField.set
import projekt.cloud.piece.xposed.api.find.field.SetField.setExists
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
    fun callfivityFinish(activity: Activity) {
        // A quick finish() call with xposed callMethod()
        method("finish").call(activity)
        // DSL-styled
        method {
            method = "finish"   // No param exist in the method signature
            call(activity)  // Ignore result
        }// .call(activity) to obtain result
    }

    @Test
    fun callResolveDialogTheme(context: Context, @StyleRes style: Int) {
        // DSL style
        method {
            method = "resolveDialogTheme"
            // paramsObj = arrayOf(context, style): Same as below params()
            params(context, style)          // Same as [callActivityFinish], not to call params() no arg required to put
            clazz = AlertDialog::class.java
            // import projekt.cloud.piece.xposed.api.find.BaseClassWrapper.Companion.static
            static()    // Mark as static
        }.call()        // Same as `callStatic()`

        // Builder style
        method("resolveDialogTheme", context, style)
            .clazz(AlertDialog::class.java)     // Same as `.static(AlertDialog::class.java)`
            .static()   // import projekt.cloud.piece.xposed.api.find.BaseClassWrapper.Companion.static
            .call()
    }
    
    @Test
    fun hookActivityOnCreate(classLoader: ClassLoader?, loadPackageParam: XC_LoadPackage.LoadPackageParam?) {
        // DSL style
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
            .clazz(Activity::class.java)
            .before {
                // Before onCreate called
            }
            .after {
                // After onCreate called
            }
            .hook()
    }

    fun hookDialogConstructor(classLoader: ClassLoader?, loadPackageParam: XC_LoadPackage.LoadPackageParam?) {
        // DSL style
        constructor {
            clazz = Dialog::class.java
            params(Context::class.java)
            before {
                // Before onCreate called
            }
            after {
                // After onCreate called
            }
            hook()  // Ignore result
        }

        // Builder-like style
        constructor("android.app.Dialog", loadPackageParam, Context::class.java)
            .before {
                // Before onCreate called
            }
            .after {
                // After onCreate called
            }
            .hook()
    }
    
    fun accessActivityMenuInflater(activity: Activity) {
        // DSL style
        var menuInflater: MenuInflater =
            field<MenuInflater> {
                clazz = Activity::class.java
                name = "menuInflater"
            }.get(activity)     // [] can by applied, where the same as `get(...)`
        
        // Builder-like style with `[]` applied
        menuInflater =
            field<MenuInflater>(Dialog::class.java, "menuInflater")[activity]
        
        // Safely nullable, prevent from crash
        val menuInflaterNullable: MenuInflater? =
            field<MenuInflater>(Dialog::class.java, "menuInflater")
                .getExists(activity)
    }
    
    fun setActivityAutofillManager(activity: Activity) {
        field<AutofillManager> {
            clazz = Activity::class.java
            name = "mAutofillManager"
        }.set(activity,
            activity.getSystemService(AutofillManager::class.java)
        )   // [] can by applied, where the same as `get(...)`
    
        // Builder-like style with `[]` applied
        field<AutofillManager>(Activity::class.java, "mAutofillManager")[activity] =
            activity.getSystemService(AutofillManager::class.java)
    
        // Safely nullable, prevent from crash
        field<AutofillManager>(Activity::class.java, "mAutofillManager")
            .setExists(activity, activity.getSystemService(AutofillManager::class.java))
    }
    
}