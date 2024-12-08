package step1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["박재성", "제이슨"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person = introduce {
            name("박재성")
            company("우아한형제들")
        }
        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.company).isEqualTo("우아한형제들")
    }

    @Test
    fun skills() {
        // Given
        val skill = skills {
            soft("Problem-solving")
            hard("Kotlin")
        }

        // Then
        assertTrue(skill.soft.contains("Problem-solving"))
        assertTrue(skill.hard.contains("Kotlin"))
    }

    @Test
    fun languages() {
        // Given
        val languageList = languages {
            "Korean" level 5
            "English" level 3
        }

        // Then
        assertEquals(2, languageList.size)
        assertEquals("Korean", languageList[0].name)
        assertEquals(5, languageList[0].level)
        assertEquals("English", languageList[1].name)
        assertEquals(3, languageList[1].level)
    }
}
