package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import study.domain.Language
import study.domain.Language.Companion.level
import study.domain.Person.Companion.introduce

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
    fun nameTest(name: String) {
        val person = introduce { name(name) }
        person.name shouldBe name
    }

    @Test
    fun companyTest() {
        val person = introduce {
            name("안성재")
            company("호두랩스")
        }
        person.company shouldBe "호두랩스"
    }

    @Test
    fun skillsTest() {
        val person = introduce {
            name("안성재")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.skills shouldBe listOf(
            "A passion for problem solving",
            "Good communication skills",
            "Kotlin",
        )
    }

    @Test
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
