package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["디디", "루루", "라"])
    fun dsl(input: String) {
        val company = "우아한형제들"
        val person: Person = introduce {
            name(input)
            company(company)
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

        assertThat(person.name).isEqualTo(input)
        assertThat(person.company).isEqualTo(company)
        assertThat(person.skills.softSkills).containsExactly("A passion for problem solving", "Good communication skills")
        assertThat(person.skills.hardSkills).containsExactly("Kotlin")
        assertThat(person.languages.languages).extractingByKeys("Korean", "English")
            .containsExactly(5, 3)
    }

    private fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }
}


