package study

import dsl.Hard
import dsl.Language
import dsl.Person
import dsl.PersonBuilder
import dsl.Soft
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun languages() {
        val person = introduce {
            name("김종성")
            company("카엔테")
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

        with(person) {
            assertThat(person.name).isEqualTo("김종성")
            assertThat(person.company).isEqualTo("카엔테")
            assertThat(skills.skills).containsExactly(
                Soft("A passion for problem solving"),
                Soft("Good communication skills"),
                Hard("Kotlin")
            )
            assertThat(languages.languages).containsExactly(
                Language("Korean", 5),
                Language("English", 3)
            )
        }
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
