package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("이수민")
 *   company("카카오")
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
    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "이수민"])
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }

        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person = introduce {
            name("이수민")
            company("카카오")
        }

        person.name shouldBe "이수민"
        person.company shouldBe "카카오"
        person.skills.shouldBeNull()
    }

    @Test
    fun skills() {
        val person = introduce {
            name("이수민")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "이수민"
        person.company shouldBe "카카오"
        person.skills?.value shouldBe listOf(Skill("soft", "A passion for problem solving"), Skill("soft", "Good communication skills"), Skill("hard", "Kotlin"))
        person.languages.shouldBeNull()
    }

    @Test
    fun languages() {
        val person = introduce {
            name("이수민")
            company("카카오")
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

        person.name shouldBe "이수민"
        person.company shouldBe "카카오"
        person.skills?.value shouldBe listOf(Skill("soft", "A passion for problem solving"), Skill("soft", "Good communication skills"), Skill("hard", "Kotlin"))
        person.languages?.value shouldBe listOf(Language("Korean", 5), Language("English", 3))
    }
}
