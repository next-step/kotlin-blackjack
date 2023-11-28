package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun dslTest() {
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

        assertThat(person.skills[0]::class).isEqualTo(Skill.Soft::class)
        assertThat(person.skills[0].value).isEqualTo("A passion for problem solving")
        assertThat(person.skills[1]::class).isEqualTo(Skill.Soft::class)
        assertThat(person.skills[1].value).isEqualTo("Good communication skills")
        assertThat(person.skills[2]::class).isEqualTo(Skill.Hard::class)
        assertThat(person.skills[2].value).isEqualTo("Kotlin")

        assertThat(person.languages[0].type).isEqualTo("Korean")
        assertThat(person.languages[0].level).isEqualTo(5)
        assertThat(person.languages[1].type).isEqualTo("English")
        assertThat(person.languages[1].level).isEqualTo(3)
    }
}