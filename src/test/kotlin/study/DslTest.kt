package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import step1.language.Language
import step1.person.Person
import step1.person.introduce
import step1.skill.Level
import step1.skill.Skill

class DslTest {

    @ValueSource(strings = ["최준규", "퍼그"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person = introduce {
            name("최준규")
            company("카카오")
        }
        assertThat(person.company).isEqualTo("카카오")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("최준규")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.skills.values).hasSize(3)
        assertThat(person.skills.values).containsOnly(
            Skill(Level.SOFT, "A passion for problem solving"),
            Skill(Level.SOFT, "Good communication skills"),
            Skill(Level.HARD, "Kotlin")
        )
    }

    @Test
    fun languages() {
        val person = introduce {
            name("최준규")
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
        assertThat(person.languages.values).hasSize(2)
        assertThat(person.languages.values).containsOnly(
            Language("Korean", 5), Language("English", 3)
        )
    }
}
