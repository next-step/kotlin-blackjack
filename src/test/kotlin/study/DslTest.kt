package study

import org.assertj.core.api.Assertions.assertThat
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
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("서정국")
            company("인프랩")
        }
        assertThat(person.name).isEqualTo("서정국")
        assertThat(person.company).isEqualTo("인프랩")
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
        val (skill1, skill2, skill3) = person.skills
        assertThat(skill1).isEqualTo(Skill("soft", "A passion for problem solving"))
        assertThat(skill2).isEqualTo(Skill("soft", "Good communication skills"))
        assertThat(skill3).isEqualTo(Skill("hard", "Kotlin"))
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("서정국")
            company("인프랩")
            languages(Language("Korean", 5))
            languages(Language("English", 3))
        }
        assertThat(person.languages[0]).isEqualTo("\"Korean\" level 5")
        assertThat(person.languages[1]).isEqualTo("\"English\" level 3")
    }
}
