package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PersonTests {

    @Test
    fun introduce() {
        val person: Person = introduce {
            name("안운장")
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

        assertAll(
            { assertThat(person.name.value).isEqualTo("안운장") },
            { assertThat(person.company.value).isEqualTo("우아한형제들") },
            {
                assertThat(person.skills).containsExactly(
                    Skill("A passion for problem solving", SkillType.SOFT),
                    Skill("Good communication skills", SkillType.SOFT),
                    Skill("Kotlin", SkillType.HARD)
                )
            },
            {
                assertThat(person.languages).containsExactly(
                    Language("Korean", 5),
                    Language("English", 3)
                )
            }
        )
    }
}
