package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PersonTest : StringSpec({
    "introduce name" {
        forAll(
            row("박재성"),
            row("제이슨"),
        ) {
            val person = introduce {
                name(it)
            }

            person.name shouldBe it
        }
    }

    "introduce name and company" {
        val person = introduce {
            name("박재성")
            company("우아한형제들")
        }

        person.name shouldBe "박재성"
        person.company shouldBe "우아한형제들"
    }

    "introduce name, company, and skills" {
        val person = introduce {
            name("박재성")
            company("우아한형제들")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "박재성"
        person.company shouldBe "우아한형제들"
        person.skills shouldBe Skills(
            softSkills = listOf("A passion for problem solving", "Good communication skills"),
            hardSkills = listOf("Kotlin")
        )
    }

    "introduce name, company, skills, and languages" {
        val person = introduce {
            name("박재성")
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

        person.name shouldBe "박재성"
        person.company shouldBe "우아한형제들"
        person.skills shouldBe Skills(
            softSkills = listOf("A passion for problem solving", "Good communication skills"),
            hardSkills = listOf("Kotlin")
        )
        person.languages shouldBe Languages(
            listOf(
                Language("Korean", 5),
                Language("English", 3),
            )
        )
    }
})
