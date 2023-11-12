package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val person = introduce {
            name("홍길동")
        }

        assertThat(person.name).isEqualTo("홍길동")
    }

    @Test
    fun company() {
        val person = introduce {
            company("우아한형제들")
        }

        assertThat(person.company).isEqualTo("우아한형제들")
    }

    @Test
    fun skills() {
        val person = introduce {
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(person.skills[0]).isEqualTo(SoftSkill("A passion for problem solving"))
        assertThat(person.skills[1]).isEqualTo(SoftSkill("Good communication skills"))
        assertThat(person.skills[2]).isEqualTo(HardSkill("Kotlin"))
    }

    @Test
    fun languages() {
        val person = introduce {
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.languages[0]).isEqualTo(Language("Korean", 5))
        assertThat(person.languages[1]).isEqualTo(Language("English", 3))
    }
}
