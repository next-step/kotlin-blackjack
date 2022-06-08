package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["강성호", "한"])
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
            name("강성호")
            company("카카오")
        }
        assertThat(person.name).isEqualTo("강성호")
        assertThat(person.company).isEqualTo("카카오")
    }

    @Test
    fun skill() {
        val person = introduce {
            name("강성호")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.name).isEqualTo("강성호")
        assertThat(person.company).isEqualTo("카카오")
        val skills = Skills().apply {
            soft("A passion for problem solving")
            soft("Good communication skills")
            hard("Kotlin")
        }
        assertThat(person.skills).isEqualTo(skills)
    }

    @Test
    fun language() {
        val person = introduce {
            name("강성호")
            company("카카오")
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
        assertThat(person.name).isEqualTo("강성호")
        assertThat(person.company).isEqualTo("카카오")
        val skills = Skills().apply {
            soft("A passion for problem solving")
            soft("Good communication skills")
            hard("Kotlin")
        }

        assertThat(person.skills).isEqualTo(skills)

        val languages = Languages().apply {
            "Korean" level 5
            "English" level 3
        }
        assertThat(person.languages).isEqualTo(languages)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
