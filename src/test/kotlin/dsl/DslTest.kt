package dsl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainAll
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
        person.skills shouldContainAll listOf(
            SoftSkill("A passion for problem solving"),
            SoftSkill("Good communication skills"),
            HardSkill("Kotlin")
        )
    }
})
