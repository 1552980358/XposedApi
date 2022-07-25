package projekt.cloud.piece.xposed.api

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_InitPackageResources
import de.robv.android.xposed.callbacks.XC_LoadPackage
import org.junit.runner.RunWith
import projekt.cloud.piece.xposed.api.find.BaseClassWrapper.Companion.clazz
import projekt.cloud.piece.xposed.api.find.BaseClassWrapper.Companion.static
import projekt.cloud.piece.xposed.api.find.BaseFindMethodWrapper.Companion.after
import projekt.cloud.piece.xposed.api.find.BaseFindMethodWrapper.Companion.before
import projekt.cloud.piece.xposed.api.find.BaseFindMethodWrapper.Companion.params
import projekt.cloud.piece.xposed.api.find.BaseFindMethodWrapper.Companion.replace
import projekt.cloud.piece.xposed.api.find.constructor.Constructor.constructor
import projekt.cloud.piece.xposed.api.find.constructor.HookConstructor.hook
import projekt.cloud.piece.xposed.api.find.field.Field.field
import projekt.cloud.piece.xposed.api.find.field.GetField.get
import projekt.cloud.piece.xposed.api.find.field.SetField.set
import projekt.cloud.piece.xposed.api.find.method.CallMethod.call
import projekt.cloud.piece.xposed.api.find.method.CallMethod.callStatic
import projekt.cloud.piece.xposed.api.find.method.HookMethod.hook
import projekt.cloud.piece.xposed.api.find.method.Method.method
import projekt.cloud.piece.xposed.api.find.method.ReplaceMethod.replace

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    class ExampleClass() {

        constructor(value: Int): this() {
            this.value = value
        }

        companion object {

            @JvmStatic
            private fun getValue(instance: ExampleClass) =
                instance.value

            @JvmStatic
            private val STATIC_VALUE = 99

        }

        private var value = 1

        fun setValue(value: Int) {
            this.value = value
        }

        private fun sum(value: Int): Int {
            // ...
            return this.value + value
        }

    }

    class MyXposedEntry: XposedEntry() {

        override fun onZygote(startupParam: IXposedHookZygoteInit.StartupParam) {
            // super method is redundant, just remove it if you like
            // super.onZygote(startupParam)
        }

        override fun onLoadPackage(loadPackageParam: XC_LoadPackage.LoadPackageParam) {
            // super method is redundant, just remove it if you like
            // super.onLoadPackage(loadPackageParam)
            methodHookingExample(loadPackageParam)
            constructorHookingExample(loadPackageParam)
            fieldHookingExample(loadPackageParam)
        }

        private fun methodHookingExample(loadPackageParam: XC_LoadPackage.LoadPackageParam) {
            // Member method
            // DSL-like
            method {
                clazz = ExampleClass::class.java

                method = "setValue"     // Same as `method("setValue", Int::class.java)`
                params(Int::class.java)

                // import projekt.cloud.piece.xposed.api.find.BaseFindMethodWrapper.Companion.before
                before {
                    // Before method is called
                }

                after {
                    // After method is called
                }

                // Hook it
                // import projekt.cloud.piece.xposed.api.find.method.HookMethod.hook
                hook()
            }
            // Builder-like
            method("setValue", Int::class.java)
                // Set class with full class name
                .clazz("projekt.cloud.piece.xposed.api.ExampleInstrumentedTest:ExampleClass", loadPackageParam)
                .before {
                    // Before method is called
                }.after {
                    // After method is called
                }.hook()    // import projekt.cloud.piece.xposed.api.find.method.HookMethod.hook

            // Static method
            method {
                clazz = ExampleClass::class.java

                method = "getValue"
                params(ExampleClass::class.java)

                // import projekt.cloud.piece.xposed.api.find.BaseFindMethodWrapper.Companion.before
                before {
                    // Before method is called
                }

                after {
                    // After method is called
                }

                static()      // Mark as static

                // Hook it
                // import projekt.cloud.piece.xposed.api.find.method.HookMethod.hook
                hook()
            }
            // All similar as above DSL style, but can have directly call next method
            method("getValue", ExampleClass::class.java)
                .before {  }
                .after {  }
                .static()
                .hook()

            // Replacement
            method("getValue", ExampleClass::class.java) {
                // import projekt.cloud.piece.xposed.api.find.BaseFindMethodWrapper.Companion.replace
                replace {   // Replacement
                    // Replace origin result
                    setResult(0)
                }
                // import projekt.cloud.piece.xposed.api.find.method.ReplaceMethod.replace
                replace()   // Replace method
            }

            // Requirement for calling method
            method {
                clazz = ExampleClass::class.java
                method("setValue", Int::class.java)
                after {
                    // Call member method
                    val sum = method("sum", 10)
                        .clazz(ExampleClass::class.java)
                        .call(it.thisObject)        // thisObject should be instance of ExampleClass inside `after` block
                    // Call static method
                    val value = method {
                        clazz = ExampleClass::class.java
                        method("getValue", it.thisObject)
                    }.callStatic()      // Integration of .static() and .call()
                }
            }

        }

        private fun constructorHookingExample(loadPackageParam: XC_LoadPackage.LoadPackageParam) {
            // DSL-like
            constructor {
                clazz = ExampleClass::class.java
                params = arrayOf(Int::class.java)       // Array allowed, same as params()

                // import projekt.cloud.piece.xposed.api.find.BaseFindMethodWrapper.Companion.before
                before {
                    // Before method is called
                }

                // import projekt.cloud.piece.xposed.api.find.BaseFindMethodWrapper.Companion.after
                after {
                    // After method is called
                }

                // Hook it
                // import projekt.cloud.piece.xposed.api.find.constructor.HookConstructor.hook
                hook()
            }

            // Builder-like         // If no params
            constructor(ExampleClass::class.java)
                .before {
                    // Before method is called
                }.after {
                    // After method is called
                }.hook()    // import projekt.cloud.piece.xposed.api.find.constructor.HookConstructor.hook
        }

        private fun fieldHookingExample(loadPackageParam: XC_LoadPackage.LoadPackageParam) {
            constructor(ExampleClass::class.java)
                .before {
                    // Before method is called
                }.after {
                    // After method is called

                    // Get `value`
                    // If it is an static field, it will be the same procedure
                    var value = field<Int> {
                        clazz = ExampleClass::class.java
                        name = "value"
                    }.get(it.thisObject)

                    // Directly get with operator get
                    value = field<Int>("projekt.cloud.piece.xposed.api.ExampleInstrumentedTest:ExampleClass", loadPackageParam, "value")[it.thisObject]

                    // Set `value`
                    // If it is an member field
                    value = 2
                    field<Int> {
                        clazz = ExampleClass::class.java
                        name = "value"
                    }[it.thisObject] = value

                    // If it is an static field, apply with lambda
                    field<Int>(ExampleClass::class.java, "STATIC_VALUE").set(value)
                    
                }.hook()    // import projekt.cloud.piece.xposed.api.find.constructor.HookConstructor.hook
            
        }

        override fun onLoadResources(initPackageResourcesParam: XC_InitPackageResources.InitPackageResourcesParam) {
            // super method is redundant, just remove it if you like
            // super.onLoadResources(initPackageResourcesParam)
        }

    }
    
}