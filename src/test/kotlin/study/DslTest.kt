package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("홍길동")
 *   company("활빈당")
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
    @ValueSource(strings = ["홍길동", "홍"])
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
                name("홍길동")
                company("활빈당")
            }
        person.company shouldBe "활빈당"
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }

        person.skills?.soft shouldBe listOf(Skill.Soft("A passion for problem solving"), Skill.Soft("Good communication skills"))
        person.skills?.hard shouldBe listOf(Skill.Hard("Kotlin"))
    }

    @Test
    fun languages() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
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

        person.languages!!.languages[0].language shouldBe "Korean"
        person.languages!!.languages[0].level shouldBe 5
        person.languages!!.languages[1].language shouldBe "English"
        person.languages!!.languages[1].level shouldBe 3
    }
}
