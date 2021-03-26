package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HumanTest {
    @Test
    fun `코틀린 dsl 실습 테스트`() {
        val human = introduce {
            name("박찬인")
            company("우아한형제들")
            skills {
                soft("잘 말하기")
                soft("잘 쓰기")
                soft("잘 듣기")
                hard("Spring boot")
            }
            languages {
                "Korean" level 5
                "English" level 1
            }
        }

        assertThat(human.company).isEqualTo("우아한형제들")
        assertThat(human.skills.skills()).containsAll(
            listOf(
                Skill.Soft("잘 말하기"),
                Skill.Soft("잘 쓰기"),
                Skill.Soft("잘 듣기"),
                Skill.Hard("Spring boot")
            )
        )
        assertThat(human.language.values()).containsEntry("Korean", 5)
        assertThat(human.language.values()).containsEntry("English", 1)
    }
}
