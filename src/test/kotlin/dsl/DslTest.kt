package dsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @ValueSource(strings = ["머스크", "트럼프"])
    @ParameterizedTest
    fun name(name: String) {
        val person =
            introduce {
                name(name)
            }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("머스크")
                company("테슬라")
            }
        person.company shouldBe "테슬라"
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("머스크")
                company("테슬라")
                skills {
                    skill(SkillType.SOFT, "커뮤니케이션")
                    skill(SkillType.SOFT, "협업")
                    skill(SkillType.HARD, "리딩")
                }
            }
        person.skills.getSkills(SkillType.SOFT) shouldBe listOf("커뮤니케이션", "협업")
        person.skills.getSkills(SkillType.HARD) shouldBe listOf("리딩")
    }
}
