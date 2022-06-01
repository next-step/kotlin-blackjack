package dsl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DslTest : FunSpec({
    test("코틀린 DSL 실습 1") {
        val person = introduce {
            name("로빈")
        }

        person.name shouldBe "로빈"
    }

    test("코틀린 DSL 실습 2") {
        val person = introduce {
            name("로빈")
            company("카카오")
        }

        person.name shouldBe "로빈"
        person.company shouldBe "카카오"
    }
})
