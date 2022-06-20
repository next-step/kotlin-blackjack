package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DslTest {
    @Test
    fun introduce() {
        val person = introduce {
            name("장재주")
            company("우아한 형제들")
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

        assertAll({
            assertThat(person.name).isEqualTo("장재주")
            assertThat(person.company).isEqualTo("우아한 형제들")
            assertThat(person.skills).hasSize(3)
            assertThat(person.skills[0].type).isEqualTo(SkillType.SOFT)
            assertThat(person.skills[0].description).isEqualTo("A passion for problem solving")
            assertThat(person.skills[1].type).isEqualTo(SkillType.SOFT)
            assertThat(person.skills[1].description).isEqualTo("Good communication skills")
            assertThat(person.skills[2].type).isEqualTo(SkillType.HARD)
            assertThat(person.skills[2].description).isEqualTo("Kotlin")
            assertThat(person.languages).hasSize(2)
            assertThat(person.languages["Korean"]).isEqualTo(5)
            assertThat(person.languages["English"]).isEqualTo(3)
       })
    }
}
