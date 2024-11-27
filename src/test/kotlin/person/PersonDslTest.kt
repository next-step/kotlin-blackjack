package person

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import person.language.Language
import person.skill.Skill
import person.skill.SkillType

class PersonDslTest {

    @Test
    fun `자기 소개를 입력하면 Person 객체를 받을 수 있다`() {
        val person = introduce {
            name("강지회")
            company("벨랩(vallab)")
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
        assertThat(person.name).isEqualTo("강지회")
        assertThat(person.company).isEqualTo("벨랩(vallab)")

        person.name shouldBe "강지회"
        person.company shouldBe "벨랩(vallab)"
        person.skills shouldContainAll listOf(
            Skill(SkillType.SOFT, "A passion for problem solving"),
            Skill(SkillType.SOFT, "Good communication skills"),
            Skill(SkillType.HARD, "Kotlin"),
        )
        person.languages shouldContainAll listOf(
            Language("Korean", 5),
            Language("English", 3),
        )
    }
}

