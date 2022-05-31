package study

import kotlindsl.Language
import kotlindsl.Skill
import kotlindsl.SkillType.HARD
import kotlindsl.SkillType.SOFT
import kotlindsl.introduce
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DslTest {

    @ParameterizedTest
    @CsvSource(
        "김주태,SK Planet", "김주태2,SK Planet"
    )
    fun introduceTest(expectedName: String, expectedCompany: String) {

        val expectedSkills = listOf(
            Skill("A passion for problem solving", SOFT),
            Skill("Good communication skills", SOFT),
            Skill("Kotlin", HARD)
        )

        val expectedLanguages = listOf(
            Language("Korean", 5),
            Language("English", 3)
        )

        val person = introduce {
            name(expectedName)
            company(expectedCompany)

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
            { assertThat(person.name).isEqualTo(expectedName) },
            { assertThat(person.company).isEqualTo(expectedCompany) },
            { assertThat(person.skills).isEqualTo(expectedSkills) },
            { assertThat(person.languages).isEqualTo(expectedLanguages) }
        )
    }
}
