package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LanguagesBuilderTest {
    @Test
    fun `build languages`() {
        val languages = LanguagesBuilder().apply {
            "Kotlin" to 3
            "Java" to 2
        }.build()

        assertThat(languages).isEqualTo(
            Languages(
                mapOf(
                    "Kotlin" to 3,
                    "Java" to 2,
                )
            )
        )
    }
}