package yampi.msh.abohava

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import yampi.msh.abohava.data.ds.db.AbohavaDatabase

@HiltAndroidApp
class AbohavaApp : Application() {

//    lateinit var appComponent: AppComponent
//        private set

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AbohavaDatabase.getDatabase(this) }


    //    val Context.appComponent: AppComponent
//        get() = when (this) {
//            is AbohavaApp -> appComponent
//            else -> applicationContext.appComponent
//        }
}