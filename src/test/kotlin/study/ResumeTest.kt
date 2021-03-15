package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {

    @Test
    fun name() {
        val person: Person = introduce {
            name("정주영")
        }

        assertThat(person.name).isEqualTo("정주영")
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("정주영")
            company("우아한형제들")
        }

        assertThat(person.company).isEqualTo("우아한형제들")
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("정주영")
            company("우아한형제들")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        val expect = listOf(
            Skill.Soft("A passion for problem solving"),
            Skill.Soft("Good communication skills"),
            Skill.Hard("Kotlin")
        )

        assertThat(person.skills.skills).isEqualTo(expect)
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("정주영")
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

        val expect = mapOf<String, Int>(
            "Korean" to 5,
            "English" to 3
        )

        assertThat(person.languages.values).isEqualTo(expect)
    }
}
