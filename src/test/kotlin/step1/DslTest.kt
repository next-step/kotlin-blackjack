package step1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import step1.language.Language
import step1.language.Languages
import step1.skill.Skill
import step1.skill.SkillLevel
import step1.skill.Skills

class DslTest {
    @Test
    fun introduce() {
        val person = introduce { }

        assertThat(person.name).isEqualTo(PersonBuilder.DEFAULT_NAME)
    }

    @ParameterizedTest
    @ValueSource(strings = ["배수정", "Vivian"])
    fun name(value: String) {
        val person = introduce {
            name(value)
        }

        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val company = "카카오"

        val person = introduce {
            company(company)
        }

        assertThat(person.company).isEqualTo(company)
    }

    @Test
    fun skills() {
        val softSkill1 = "A passion for problem solving"
        val softSKill2 = "Good communication skills"
        val hardSkill1 = "Kotlin"

        val person = introduce {
            skills {
                soft(softSkill1)
                soft(softSKill2)
                hard(hardSkill1)
            }
        }

        val expectedSkills = Skills(
            listOf(
                Skill(SkillLevel.SOFT, softSkill1),
                Skill(SkillLevel.SOFT, softSKill2),
                Skill(SkillLevel.HARD, hardSkill1)
            )
        )

        assertThat(person.skills.list).isEqualTo(expectedSkills.list)
    }

    @Test
    fun languages() {
        val koreanText = "Korean"
        val koreanLevel = 5
        val englishText = "English"
        val englishLevel = 3

        val person: Person = introduce {
            languages {
                koreanText level koreanLevel
                englishText level englishLevel
            }
        }

        val expectedLanguages = Languages(
            listOf(
                Language(koreanText, koreanLevel),
                Language(englishText, englishLevel)
            )
        )

        assertThat(person.languages.list).isEqualTo(expectedLanguages.list)
    }
}
