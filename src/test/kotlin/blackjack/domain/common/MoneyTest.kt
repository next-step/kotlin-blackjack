package blackjack.domain.common

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MoneyTest : DescribeSpec({

    describe("constructor") {
        it("금액을 생성한다") {
            listOf(0, 1_000).forAll { amount ->
                Money.of(amount) shouldNotBe null
            }
        }
    }

    describe("add") {
        it("두 금액을 더할 수 있다") {
            Money.of(100) + Money.of(200) shouldBe Money.of(300)
        }
    }

    describe("minus") {
        it("두 금액을 뺄 수 있다") {
            Money.of(100) - Money.of(200) shouldBe Money.of(-100)
        }
    }

    describe("multiply") {
        it("두 금액을 곱할 수 있다") {
            val money = Money.of(100)

            money.multiply(1.5) shouldBe Money.of(150)
        }
    }

    describe("compareTo") {
        it("두 금액을 비교할 수 있다.") {
            assertSoftly {
                (Money.of(0) == Money.of(0)) shouldBe true
                (Money.of(0) > Money.of(100)) shouldBe false
                (Money.of(0) < Money.of(100)) shouldBe true
            }
        }
    }
})
