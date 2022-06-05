package study

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import study.language.Language
import study.skill.Skill

class DslTest : FreeSpec({

    "introduce" {
        val person: Person = introduce {
            name("우주")
        }

        person.name shouldBe "우주"
    }

    "company" {
        val person: Person = introduce {
            name("우주")
            company("인프랩")
        }

        person.name shouldBe "우주"
        person.company shouldBe "인프랩"
    }

    "skills" {
        val person: Person = introduce {
            name("우주")
            skills {
                soft("A passion for problem solving")
                hard("Kotlin")
            }
        }

        person.name shouldBe "우주"
        person.skills.soft shouldBe listOf(Skill("A passion for problem solving"))
        person.skills.hard shouldBe listOf(Skill("Kotlin"))
    }

    "languages" {
        val person: Person = introduce {
            name("우주")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        person.name shouldBe "우주"
        person.languages.value shouldBe listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
})
