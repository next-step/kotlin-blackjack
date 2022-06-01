package dsl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DslTest : FunSpec({
    test("코틀린 DSL 실습 - name") {
        val person = introduce {
            name("로빈")
        }

        person.name shouldBe "로빈"
    }

    test("코틀린 DSL 실습 - company") {
        val person = introduce {
            name("로빈")
            company("카카오")
        }

        person.name shouldBe "로빈"
        person.company shouldBe "카카오"
    }

    test("코틀린 DSL 실습 - skills") {
        val person = introduce {
            name("로빈")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "로빈"
        person.company shouldBe "카카오"
        person.skills shouldBe Skills(
            listOf(
                SoftSkill("A passion for problem solving"),
                SoftSkill("Good communication skills"),
                HardSkill("Kotlin")
            )
        )
    }

    test("코틀린 DSL 실습 - languages") {
        val person = introduce {
            name("로빈")
            company("카카오")
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

        person.name shouldBe "로빈"
        person.company shouldBe "카카오"
        person.skills shouldBe Skills(
            listOf(
                SoftSkill("A passion for problem solving"),
                SoftSkill("Good communication skills"),
                HardSkill("Kotlin")
            )
        )
        person.languages shouldBe Languages(
            listOf(
                Language("Korean", 5),
                Language("English", 3)
            )
        )
    }
})
