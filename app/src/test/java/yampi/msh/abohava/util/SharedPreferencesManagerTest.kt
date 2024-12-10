package yampi.msh.abohava.util

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SharedPreferencesManagerTest {

//    @Mock
//    lateinit var context: Context
//    lateinit var sharedPreferencesManager: SharedPreferencesManager
//    @Mock
//    lateinit var sharedPreferences: SharedPreferences
//    @Mock
//    lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    val DEVICE_ID_VALUE = "A1234567"
    val DEVICE_ID_KEY = "DEVICE_ID_KEY"

    val sharedPreferencesManager = SharedPreferencesManager.getInstance(getApplicationContext())

    @Before
    @Throws(Exception::class)
    fun setup() {
//        MockKAnnotations.init(this)
//        MockitoAnnotations.initMocks(this)

//        sharedPreferences = mockk<SharedPreferences>(relaxed = true)
//        sharedPreferencesEditor =
//            mockk<SharedPreferences.Editor>(relaxed = true)
//        context = ApplicationProvider.getApplicationContext()
//        sharedPreferencesManager = mockk<SharedPreferencesManager>(relaxed = true).apply {  }

//        every{context.getSharedPreferences(any(),any())}
//            .returns(sharedPreferences)
//        every{sharedPreferences.edit()}
//            .returns(sharedPreferencesEditor)
//        every{sharedPreferencesEditor.putString(any(), any())}
//            .returns(sharedPreferencesEditor)

        every { sharedPreferencesManager.getDeviceId() } returns DEVICE_ID_VALUE
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun getDeviceId() {

//        every { sharedPreferencesManager.getDeviceId() } returns DEVICE_ID_VALUE
        val deviceId = sharedPreferencesManager.printDeviceId()

//        verify { sharedPreferencesManager.getDeviceId() }

        Assert.assertEquals(DEVICE_ID_VALUE, deviceId)
    }

    @Test
    fun setDeviceId() {
//        every { sharedPreferencesEditor.putString(any(), any()) } returns sharedPreferencesEditor
//
//        sharedPreferencesManager.setDeviceId(DEVICE_ID_VALUE)
//
//        verify { sharedPreferencesEditor.putString(any(), any()) }
    }
}