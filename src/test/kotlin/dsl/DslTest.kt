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
            Skill("A passion for problem solving"),
            Skill("Good communication skills"),
        )

        person.skills.hard shouldBe listOf(
            Skill("Kotlin")
        )
    }

    "languages Test" {
        val person = introduce {
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        person.languages shouldBe listOf(
            Language("Korean", 5),
            Language("English", 3),
        )
    }

    "introduct Test" {
        val person = introduce {
            name("이름")
            company("멋진회사")
            skills {
                soft("자바")
                hard("코틀린")
            }
            languages {
                "한국어" level 100
            }
        }

        person.name shouldBe "이름"
        person.company shouldBe "멋진회사"
        person.skills.soft shouldBe listOf(Skill("자바"))
        person.skills.hard shouldBe listOf(Skill("코틀린"))
        person.languages shouldBe listOf(Language("한국어", 100))
    }
})
