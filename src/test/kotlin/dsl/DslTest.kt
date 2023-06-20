package dsl

import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class DslTest : FreeSpec({

    "introduce 생성" - {
        withData(
            "제이든",
            "김진억"
        ) { value ->
            val person = introduce {
                name(value)
            }
            person.name shouldBe value
        }
    }

    "company 등록" - {
        withData(
            "카카오",
            "넥스트스탭"
        ) { companyName ->
            val name = "김진억"

            val person = introduce {
                name(name)
                company(companyName)
            }

            person.name shouldBe name
            person.company shouldBe companyName
        }
    }

    "skill 등록" - {
        withData(
            mapOf(
                "case01" to SkillTestData(listOf("A passion for problem solving", "Good communication skills"), listOf("Kotlin")),
                "case02" to SkillTestData(listOf("잘먹는다", "잘잔다"), listOf("자바", "파이썬"))
            )

        ) { (soft, hard) ->
            val name = "김진억"
            val company = "카카오"

            val person = introduce {
                name(name)
                company(company)
                skills {
                    soft.forEach { soft(it) }
                    hard.forEach { hard(it) }
                }
            }

            person.name shouldBe name
            person.company shouldBe company
            person.skills!!.soft shouldContainAll soft
            person.skills!!.hard shouldContainAll hard
        }
    }

    "language 등록" - {
        withData(
            LanguageTestData(listOf("Korean" to 5, "English" to 3)),
            LanguageTestData(listOf("Spanish" to 5, "Japanese" to 1, "French" to 1))
        ) { (values) ->
            val name = "김진억"
            val company = "카카오"
            val softSkills = listOf("A passion for problem solving", "Good communication skills")
            val hardSkills = listOf("Kotlin")

            val person = introduce {
                name(name)
                company(company)
                skills {
                    soft(softSkills[0])
                    soft(softSkills[1])
                    hard(hardSkills[0])
                }
                languages {
                    values.forEach { (language, lv) ->
                        language level lv
                    }
                }
            }

            person.name shouldBe name
            person.company shouldBe company
            person.skills!!.soft[0] shouldBe softSkills[0]
            person.skills!!.soft[1] shouldBe softSkills[1]
            person.skills!!.hard[0] shouldBe hardSkills[0]
            person.languages shouldContainAll values.map { Language(it.first, it.second) }
        }
    }
}) {
    companion object {
        data class SkillTestData(
            val soft: List<String>,
            val hard: List<String>,
        )
    }

    data class LanguageTestData(val values: List<Pair<String, Int>>)
}
