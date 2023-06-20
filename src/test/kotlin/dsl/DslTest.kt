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
                skill {
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
}) {
    companion object {
        data class SkillTestData(
            val soft: List<String>,
            val hard: List<String>
        )
    }
}
