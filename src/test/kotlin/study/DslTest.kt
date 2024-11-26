package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import study.dsl.introduce
import kotlin.streams.asStream

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

    @CsvSource(
        "A passion for problem solving, Kotlin",
        "Good communication skills, Kotlin",
        "Critical thinking, Java",
    )
    @ParameterizedTest
    fun skills(
        softSkill: String,
        hardSkill: String,
    ) {
        val person =
            introduce {
                name("이규원")
                company("한빛앤")
                skills {
                    soft(softSkill)
                    hard(hardSkill)
                }
            }

        person.skills?.soft shouldBe listOf(softSkill)
        person.skills?.hard shouldBe listOf(hardSkill)
    }

    companion object {
        @JvmStatic
        fun languageData() =
            sequenceOf(
                mapOf("Korean" to 5, "English" to 3),
                mapOf("Japanese" to 4, "Spanish" to 2),
                mapOf("French" to 3, "German" to 4),
            ).asStream()
    }

    @MethodSource("languageData")
    @ParameterizedTest
    fun languages(languages: Map<String, Int>) {
        val person =
            introduce {
                name("이규원")
                company("한빛앤")
                languages {
                    languages.forEach { (lang, level) ->
                        lang level level
                    }
                }
            }

        person.languages?.languages shouldBe languages
    }
}
