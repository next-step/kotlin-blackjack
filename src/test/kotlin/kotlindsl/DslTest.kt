package kotlindsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun `DSL을 이용하여 이력서 객체를 만들어본다`() {
        val resume = introduce {
            name("홍보미")
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

        val expectedSkills = listOf(
            Skill.Soft("A passion for problem solving"),
            Skill.Soft("Good communication skills"),
            Skill.Hard("Kotlin")
        )

        val expectedLanguages = listOf(
            Language("Korean", 5), Language("English", 3)
        )

        assertThat(resume.name).isEqualTo("홍보미")
        assertThat(resume.company).isEqualTo("카카오")
        assertThat(resume.languages).isEqualTo(expectedLanguages)
        assertThat(resume.skills).isEqualTo(expectedSkills)
    }
}
