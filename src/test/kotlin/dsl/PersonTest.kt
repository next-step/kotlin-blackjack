package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PersonTest {
    @ValueSource(strings = ["EJ", "DJ"])
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
            name("EJ")
            company("MS")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }

        }
        assertThat(person.name).isEqualTo("EJ")
        assertThat(person.company).isEqualTo("MS")

        val skills = person.skills.values
        assertThat(skills[0].description).isEqualTo("A passion for problem solving")
        assertThat(skills[1].description).isEqualTo("Good communication skills")
        assertThat(skills[2].description).isEqualTo("Kotlin")

        val languages = person.languages.values
        assertThat(languages[0].language).isEqualTo("Korean")
        assertThat(languages[0].level).isEqualTo(5)
        assertThat(languages[1].language).isEqualTo("English")
        assertThat(languages[1].level).isEqualTo(3)
    }
}
