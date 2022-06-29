package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
internal class PersonKtTest {

    @Test
    fun `introduce - 사람 생성`() {
        // given
        val testSkills = listOf(
            Skill("A passion for problem solving", SkillType.Soft),
            Skill("Good communication skills", SkillType.Soft),
            Skill("Kotlin", SkillType.Hard),
        )
        val testLanguages = listOf(
            Language("Korean", 5),
            Language("English", 3)
        )

        // when
        val person: Person = introduce {
            name("이재영")
            company("나뭇잎")
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

        // then
        assertThat(person.name).isEqualTo("이재영")
        assertThat(person.company).isEqualTo("나뭇잎")
        assertThat(person.skills).isEqualTo(testSkills)
        assertThat(person.languages).isEqualTo(testLanguages)
    }
}
