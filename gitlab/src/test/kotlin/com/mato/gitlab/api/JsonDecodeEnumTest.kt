package com.mato.gitlab.api

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class JsonDecodeEnumTest {
    enum class Month {
        January, February, March
    }

    @Serializable
    data class Birthday(
        val month: Month,
        val day: Int
    )

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json { decodeEnumsCaseInsensitive = true }
    private val errorJson = Json

    @Test
    fun testDecodeEnums() {
        val data = """
            {
                "month": "JANUARY",
                "day": 1
            }
        """.trimIndent()
        val birthday = json.decodeFromString<Birthday>(data)
        assertEquals(birthday.month, Month.January)
        assertEquals(birthday.day, 1)

        assertThrows<SerializationException> {
            errorJson.decodeFromString<Birthday>(data)
        }
    }
}