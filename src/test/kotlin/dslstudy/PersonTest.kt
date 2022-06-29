package dslstudy

import io.kotest.assertions.assertSoftly
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

        assertSoftly(person) {
            skills[0].type shouldBe Skill.Type.SOFT
            skills[0].name shouldBe "A passion for problem solving"
            skills[1].type shouldBe Skill.Type.SOFT
            skills[1].name shouldBe "Good communication skills"
            skills[2].type shouldBe Skill.Type.HARD
            skills[2].name shouldBe "Kotlin"
        }
    }

    "languages" {
        val person = introduce {
            name("김경록")
            company("맘편한세상")
            skills {
                soft("White lie")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        person.languages[0].name shouldBe "Korean"
        person.languages[0].level shouldBe 5
        person.languages[1].name shouldBe "English"
        person.languages[1].level shouldBe 3
    }
})
