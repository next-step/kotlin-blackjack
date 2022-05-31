package step1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun introduce() {
        val person: Person = introduce {
            name("임석호")
            company("노란집")
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

        assertThat(person.name).isEqualTo("임석호")
        assertThat(person.company).isEqualTo("노란집")

        val expectedSkills = listOf(
            Skill("A passion for problem solving", SkillType.Soft),
            Skill("Good communication skills", SkillType.Soft),
            Skill("Kotlin", SkillType.Hard),
        )
        assertThat(person.skills).containsAll(expectedSkills)

        val expectedLanguages = listOf(Language("Korean", 5), Language("English", 3))
        assertThat(person.languages).containsAll(expectedLanguages)
    }
}
