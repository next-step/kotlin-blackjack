package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("김보라")
 *   company("올뺌컴퍼니")
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
    @ParameterizedTest
    @ValueSource(strings = ["김보라", "홍길동"])
    fun nameTest(name: String) {
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
                name("김보라")
                company("올뺌컴퍼니")
            }

        person.company shouldBe "올뺌컴퍼니"
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("김보라")
                company("올뺌컴퍼니")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }

        person.skills.size shouldBe 3
        person.skills.map { it.description } shouldBe
            listOf(
                "A passion for problem solving",
                "Good communication skills",
                "Kotlin",
            )
    }
}
