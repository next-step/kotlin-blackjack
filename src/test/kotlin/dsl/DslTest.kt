package dsl

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
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
        val skills = Skills(listOf())

        val person = introduce {
            name("박재성")
            company("우아한형제들")
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
        assertThat(person.skills).isEqualTo("박재성")
        assertThat(person.company).isEqualTo("우아한형제들")
    }

    @Test
    fun languages() {
        val person = introduce {
            name("박재성")
            company("우아한형제들")
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

        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.company).isEqualTo("우아한형제들")
    }
}
