package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import study.domain.HardSkill
import study.domain.Language
import study.domain.Language.Companion.level
import study.domain.Person.Companion.introduce
import study.domain.SoftSkill

/**
 * introduce {
 *   name("안성재")
 *   company("호두랩스")
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
 *
 */
class DslTest {
    @ValueSource(strings = ["안성재"])
    @ParameterizedTest
    @DisplayName("name DSL 테스트")
    fun nameTest(name: String) {
        val person = introduce { name(name) }
        person.name shouldBe name
    }

    @Test
    @DisplayName("company DSL 테스트")
    fun companyTest() {
        val person = introduce {
            name("안성재")
            company("호두랩스")
        }
        person.company shouldBe "호두랩스"
    }

    @Test
    @DisplayName("skills DSL 테스트")
    fun skillsTest() {
        val person = introduce {
            name("안성재")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.skills.softSkills shouldBe listOf(
            SoftSkill("A passion for problem solving"),
            SoftSkill("Good communication skills"),
        )
        person.skills.hardSkills shouldBe listOf(
            HardSkill("Kotlin"),
        )
    }

    @Test
    @DisplayName("languages DSL 테스트")
    fun languagesTest() {
        val person = introduce {
            name("안성재")
            languages("Korean" level 5, "English" level 3)
        }
        person.languages shouldBe listOf(
            Language("Korean", 5),
            Language("English", 3),
        )
    }
}
