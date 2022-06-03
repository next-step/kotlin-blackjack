package study

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["서정국", "꾸기"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            this.name(value)
        }
        Assertions.assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("서정국")
            company("인프랩")
        }
        Assertions.assertThat(person.name).isEqualTo("서정국")
        Assertions.assertThat(person.company).isEqualTo("인프랩")
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("서정국")
            company("인프랩")
            skills(Skill("soft", "A passion for problem solving"))
            skills(Skill("soft", "Good communication skills"))
            skills(Skill("hard", "Kotlin"))
        }
        Assertions.assertThat(person.skills[0]).isEqualTo(Skill("soft", "A passion for problem solving"))
        Assertions.assertThat(person.skills[1]).isEqualTo(Skill("soft", "Good communication skills"))
        Assertions.assertThat(person.skills[2]).isEqualTo(Skill("hard", "Kotlin"))
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("서정국")
            company("인프랩")
            languages(Language("Korean", 5))
            languages(Language("English", 3))
        }
        Assertions.assertThat(person.languages[0]).isEqualTo("\"Korean\" level 5")
        Assertions.assertThat(person.languages[1]).isEqualTo("\"English\" level 3")
    }
}
