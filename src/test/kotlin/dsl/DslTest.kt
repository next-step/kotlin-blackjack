package dsl

import dsl.language.Language
import dsl.language.Languages
import dsl.skill.Skill
import dsl.skill.Skills
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun `introduce 함수에 아무것도 주어지지 않을 경우 기본 값을 가진 Person 이 반환된다`() {
        val person = introduce { }

        assertThat(person.name).isEqualTo(PersonBuilder.DEFAULT_NAME)
        assertThat(person.company).isEqualTo(PersonBuilder.DEFAULT_COMPANY)
        assertThat(person.skills.list).hasSize(0)
        assertThat(person.languages.list).hasSize(0)
    }

    @Test
    fun `name 함수에 이름이 주어질 경우 해당 이름을 가진 Person 이 반환된다`() {
        val name = "배수정"

        val person = introduce {
            name(name)
        }

        assertThat(person.name).isEqualTo(name)
    }

    @Test
    fun `company 함수에 회사 이름이 주어질 경우 해당 회사 이름을 가진 Person 이 반환된다`() {
        val company = "카카오"

        val person = introduce {
            company(company)
        }

        assertThat(person.company).isEqualTo(company)
    }

    @Test
    fun `skills 함수에 스킬셋이 주어질 경우 해당 스킬셋을 가진 Person 이 반환된다`() {
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
                Skill.Soft(softSkill1),
                Skill.Soft(softSKill2),
                Skill.Hard(hardSkill1)
            )
        )

        assertThat(person.skills.list).containsExactlyInAnyOrderElementsOf(expectedSkills.list)
    }

    @Test
    fun `languages 함수에 언어셋이 주어질 경우 해당 언어셋을 가진 Person 이 반환된다`() {
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

        assertThat(person.languages.list).containsExactlyInAnyOrderElementsOf(expectedLanguages.list)
    }
}
