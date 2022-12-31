package study

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class DslTest: DescribeSpec({

    context("introduce Test"){
        it("introduce Test") {
            val person = introduce {
                name("마르코")
                company("회사")
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

            person.name shouldBe "마르코"
            person.company shouldBe "회사"
            person.skills shouldHaveSize 3
            person.skills[0].name shouldBe "A passion for problem solving"
            person.skills[1].name shouldBe "Good communication skills"
            person.skills[2].name shouldBe "Kotlin"
            person.languages shouldHaveSize 2
            person.languages[0].type shouldBe "Korean"
            person.languages[0].level shouldBe 5
            person.languages[1].type shouldBe "English"
            person.languages[1].level shouldBe 3
        }
    }
})