package dsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PersonDslTest {
    @ValueSource(strings = ["황상욱", "홍길동"])
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
                name("황상욱")
                company("코드런")
            }
        person.name shouldBe "황상욱"
        person.company shouldBe "코드런"
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("황상욱")
                company("코드런")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("kafka")
                }
            }
        person.skills.soft shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skills.hard shouldBe listOf("kafka")
    }

    @Test
    fun languages() {
        val person =
            introduce {
                name("황상욱")
                company("코드런")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("kafka")
                }
                languages {
                    "java" level 7
                    "kotlin" level 3
                }
            }
        person.languages shouldBe mapOf("java" to 7, "kotlin" to 3)
    }
}
