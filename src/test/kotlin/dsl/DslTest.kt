package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DslTest : StringSpec({
    "객체 비교" {
        val personDsl = introduce {
            name("박영호")
            company("우아한형제들")
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

        personDsl shouldBe Person(
            name = "박영호",
            company = "우아한형제들",
            skills = Skills(
                softSkills = listOf(
                    "A passion for problem solving",
                    "Good communication skills"
                ),
                hardSkills = listOf("Kotlin")
            ),
            languages = mapOf(
                "Korean" to 5,
                "English" to 3
            )
        )
    }
})
