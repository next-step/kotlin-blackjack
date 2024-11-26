package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import study.dsl.introduce

class DslTest {
    @ValueSource(strings = ["홍길동", "이규원"])
    @ParameterizedTest
    fun name(name: String) {
        val person =
            introduce {
                name(name)
            }
        person.name shouldBe name
        person.company shouldBe null
    }

    @ValueSource(strings = ["마키나락스", "한빛앤"])
    @ParameterizedTest
    fun company(company: String) {
        val person =
            introduce {
                name("이규원")
                company(company)
            }
        person.company shouldBe company
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("이규원")
                company("한빛앤")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }

        person.skills?.soft shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skills?.hard shouldBe listOf("Kotlin")
    }

    @Test
    fun languages() {
        val person =
            introduce {
                name("이규원")
                company("한빛앤")
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

        person.skills?.soft shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skills?.hard shouldBe listOf("Kotlin")
    }
}
