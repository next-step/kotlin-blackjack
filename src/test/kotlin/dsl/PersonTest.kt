package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PersonTest : StringSpec({

    "사람은 이름, 회사, 기술 그리고 언어를 가진다." {
        val person = instroduce {
            name("YohanIO")
            company("Inflab")
            skills {
                hardSkill("Breathing")
                softSkill("Rictusempra")
            }
            languages {
                "Hangul" level 9999
            }
        }

        person.name shouldBe "YohanIO"
        person.company shouldBe "Inflab"

        person.skills[0].type shouldBe SkillType.HARD
        person.skills[0].name shouldBe "Breathing"
        person.skills[1].type shouldBe SkillType.SOFT
        person.skills[1].name shouldBe "Rictusempra"

        person.languages[0].name shouldBe "Hangul"
        person.languages[0].level shouldBe 9999
    }
})
