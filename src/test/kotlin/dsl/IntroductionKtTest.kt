package dsl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class IntroductionKtTest : FunSpec({

    context("introduce") {
        test("Person을 생성한다") {
            val actual = introduce {
                name("최진영")
                company("우아한형제들")
            }

            actual.name shouldBe "최진영"
            actual.company shouldBe "우아한형제들"
        }
    }
})
