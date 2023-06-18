package dsl

import dsl.language.Language
import dsl.skill.Skill
import dsl.skill.SkillLevel.SOFT
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class IntroductionKtTest : FunSpec({

    context("introduce") {
        test("Person을 생성한다") {
            val actual = introduce {
                name("최진영")
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

            actual.name shouldBe "최진영"
            actual.company shouldBe "우아한형제들"
            actual.skills.values shouldHaveSize 3
            actual.skills.values[0] shouldBe Skill(SOFT, "A passion for problem solving")
            actual.languages.values shouldHaveSize 2
            actual.languages.values[0] shouldBe Language("Korean", 5)
        }
    }
})
