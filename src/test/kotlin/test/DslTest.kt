package test

import dsl.introduce
import dsl.language.Language
import dsl.skill.HardSkill
import dsl.skill.SoftSkill
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["오민혁", "nooblette"])
    @ParameterizedTest
    fun introduceName(value: String) {
        val person =
            introduce {
                name(value)
            }

        person.name shouldBe value
    }

    @MethodSource("nameAndCompany")
    @ParameterizedTest
    fun introduceNameAndCompany(list: List<String>) {
        val person =
            introduce {
                name(list[0])
                company(list[1])
            }

        person.name shouldBe list[0]
        person.company shouldBe list[1]
    }

    @Test
    fun introduceSKills() {
        val softSKillDescriptions =
            listOf(
                "Good Communication skills",
                "A passion for problem solving",
            )

        val hardSKillDescriptions =
            listOf(
                "Kotlin",
            )

        val person =
            introduce {
                name("오민혁")
                company("SSG")
                skills {
                    soft(softSKillDescriptions[0])
                    soft(softSKillDescriptions[1])
                    hard(hardSKillDescriptions[0])
                }
            }

        person.skills
            ?.soft
            ?.map { (it as SoftSkill).description }
            ?.shouldContainAll(softSKillDescriptions)

        person.skills
            ?.hard
            ?.map { (it as HardSkill).description }
            ?.shouldContainAll(hardSKillDescriptions)
    }

    @Test
    fun introduceLanguage() {
        val softSKillDescriptions =
            listOf(
                "Good Communication skills",
                "A passion for problem solving",
            )

        val hardSKillDescriptions =
            listOf(
                "Kotlin",
            )

        val person =
            introduce {
                name("오민혁")
                company("SSG")
                skills {
                    soft(softSKillDescriptions[0])
                    soft(softSKillDescriptions[1])
                    hard(hardSKillDescriptions[0])
                }
                languages {
                    KOREAN level KOREAN_LEVEL
                    ENGLISH level ENGLISH_LEVEL
                }
            }

        person.languages
            ?.language
            ?.get(0) shouldBe Language(country = KOREAN, level = KOREAN_LEVEL)
        person.languages
            ?.language
            ?.get(1) shouldBe Language(country = ENGLISH, level = ENGLISH_LEVEL)
    }

    companion object {
        private const val KOREAN = "Korean"
        private const val KOREAN_LEVEL = 5

        private const val ENGLISH = "English"
        private const val ENGLISH_LEVEL = 3

        @JvmStatic
        private fun nameAndCompany() =
            listOf(
                listOf("오민혁", "SSG"),
                listOf("nooblette", "SSG"),
            )
    }
}
