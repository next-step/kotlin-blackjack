package kotlinDSL

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*
introduce {
  name("이호재")
  company("스파크")
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
 */

class DslTest {

    @ValueSource(strings = ["이호재", "그란델왈드"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }

        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("이호재")
            company("스파크")
        }

        assertThat(person.name).isEqualTo("이호재")
        assertThat(person.company).isEqualTo("스파크")
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("이호재")
            company("스파크")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        val skills = Skills().apply {
            soft("A passion for problem solving")
            soft("Good communication skills")
            hard("Kotlin")
        }

        assertThat(person.name).isEqualTo("이호재")
        assertThat(person.company).isEqualTo("스파크")
        assertThat(person.skills).isEqualTo(skills)
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("이호재")
            company("스파크")
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

        val skills = Skills().apply {
            soft("A passion for problem solving")
            soft("Good communication skills")
            hard("Kotlin")
        }

        val languages = Languages().apply {
            "Korean" level 5
            "English" level 3
        }

        assertThat(person.name).isEqualTo("이호재")
        assertThat(person.company).isEqualTo("스파크")
        assertThat(person.skills).isEqualTo(skills)
        assertThat(person.languages).isEqualTo(languages)
    }
}


fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
