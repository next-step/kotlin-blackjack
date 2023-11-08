package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("원동재")
 *   company("마이다스아이티")
 *   skills {
 *     soft("A passion for problem solving")
 *     soft("Good communication skills")
 *     hard("Kotlin")
 *   }
 *   languages {
 *     "Korean" level 5
 *     "English" level 3
 *   }
 * }
 */
class DslTest {

    @ValueSource(strings = ["원동재", "홍길동"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce(name)
        person.name shouldBe name
    }

    @ValueSource(strings = ["마이다스", "마이다스 아이티"])
    @ParameterizedTest
    fun company(companyName: String) {
        val person = introduce("원동재") {
            company(companyName)
        }
        person.name shouldBe "원동재"
        person.company shouldBe companyName
    }

    @Test
    fun skill() {
        val person = introduce("원동재") {
            company("마이다스아이티")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "원동재"
        person.company shouldBe "마이다스아이티"
        person.skills shouldBe Skills(
            hardSkills = listOf(Skill.Hard("Kotlin")),
            softSkills = listOf(
                Skill.Soft("A passion for problem solving"),
                Skill.Soft("Good communication skills")
            )
        )
    }

    @Test
    fun language() {
        val person = introduce("원동재") {
            company("마이다스아이티")
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
        person.name shouldBe "원동재"
        person.company shouldBe "마이다스아이티"
        person.skills shouldBe Skills(
            hardSkills = listOf(Skill.Hard("Kotlin")),
            softSkills = listOf(
                Skill.Soft("A passion for problem solving"),
                Skill.Soft("Good communication skills")
            )
        )
        person.languages.languages shouldBe listOf(
            Language.from("Korean", 5),
            Language.from("English", 3)
        )
    }
}
