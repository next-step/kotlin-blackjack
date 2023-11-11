package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DslTest : StringSpec({

    "name Test" {
        val name = "박재성"
        val person = introduce {
            name(name)
        }

        person.name shouldBe name
    }

    "company Test" {
        val company = "우아한형제들"
        val person = introduce {
            company(company)
        }

        person.company shouldBe company
    }

    "skills Test" {
        val person = introduce {
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.skills.soft shouldBe listOf(
            "A passion for problem solving",
            "Good communication skills",
        )

        person.skills.hard shouldBe listOf(
            "Kotlin"
        )
    }
})
