package dslpractice

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["게이츠", "잡스"])
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
                name("게이츠")
                company("MS")
            }
        person.company shouldBe "MS"
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("게이츠")
                company("MS")
                skills {
                    skill(SkillType.SOFT, "A passion for problem solving")
                    skill(SkillType.SOFT, "Good communication skills")
                    skill(SkillType.HARD, "Kotlin")
                }
            }
        person.skills.getSkills(SkillType.SOFT) shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skills.getSkills(SkillType.HARD) shouldBe listOf("Kotlin")
    }
}
