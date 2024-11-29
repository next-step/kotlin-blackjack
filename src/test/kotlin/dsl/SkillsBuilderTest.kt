package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SkillsBuilderTest {
    @Test
    fun `build skills`() {
        val skills = SkillsBuilder().apply {
            soft("soft1")
            soft("soft2")
            hard("hard1")
            "Java" to 2
        }.build()

        assertThat(skills).isEqualTo(
            Skills(
                listOf(
                    "soft1",
                    "soft2",
                ),
                listOf(
                    "hard1",
                )
            )
        )
    }
}