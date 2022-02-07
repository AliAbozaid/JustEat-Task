package io.aliabozid.justeat.restaurants

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import io.aliabozid.justeat.restaurants.data.filereader.FileReaderImpl
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.io.FileNotFoundException

@RunWith(AndroidJUnit4ClassRunner::class)
class FileReaderImplTest {
    @Test(expected = FileNotFoundException::class)
    fun test_read_json_file_fail() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val reader = FileReaderImpl(context)
        reader.getTextFromAssetFile("asd")
    }

    @Test
    fun test_read_json_file_success() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val reader = FileReaderImpl(context)
        val json = reader.getTextFromAssetFile("restaurants.json")
        val length = json.length
        assertEquals(true, length > 0)
    }
}