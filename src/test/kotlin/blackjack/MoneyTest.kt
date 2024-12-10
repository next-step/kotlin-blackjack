package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MoneyTest : StringSpec({
    "Money 는 서로 더할 수 있다" {
        val money1000 = Money(1000)
        val money2000 = Money(2000)

        val result = money1000 + money2000
        result shouldBe Money(3000)
    }

    "Money 는 뺄 수 있다" {
        val money1000 = Money(1000)
        val money2000 = Money(2000)

        val result = money1000 - money2000
        result shouldBe Money(-1000)
    }

    "Money 는 ZERO 라는 초깃값을 제공한다" {
        Money.ZERO shouldBe Money(0)
    }

    "Money는 서로 비교할 수 있다" {
        val money1 = Money(1000)
        val money2 = Money(1001)
        val money3 = Money(1000)

        (money1 < money2) shouldBe true
        (money1 == money3) shouldBe true
        (money2 > money3) shouldBe true
        (money2 == money3) shouldBe false
    }

    "Money 은 금액을 곱할 수 있다" {
        val money1000 = Money(1000)
        val multiplier = 1.5

        val result = money1000 * multiplier

        result shouldBe Money(1500)
    }

    "Money는 금액을 마이너스로 변환할 수 있다" {
        val money1000 = Money(1000)

        -money1000 shouldBe Money(-1000)
    }

    "Money 리스트는 sum()으로 합계를 구할 수 있다" {
        val list = listOf(Money(1000), Money(2000), Money(-3000))
        list.sum() shouldBe Money.ZERO
    }
})
