package dsl_study

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PersonTest : StringSpec({
    "introduce" {
        listOf("김경록", "로키").forAll {
            val person = introduce {
                name(it)
            }

            person.name shouldBe it
        }
    }

    "company" {
        val person = introduce {
            name("김경록")
            company("맘편한세상")
        }

        person.name shouldBe "김경록"
        person.company shouldBe "맘편한세상"
    }

    "skills" {
        val person = introduce {
            name("김경록")
            company("맘편한세상")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.skills[0].type shouldBe Skill.Type.SOFT
        person.skills[0].name shouldBe "A passion for problem solving"
        person.skills[1].type shouldBe Skill.Type.SOFT
        person.skills[1].name shouldBe "Good communication skills"
        person.skills[2].type shouldBe Skill.Type.HARD
        person.skills[2].name shouldBe "Kotlin"
    }
})
