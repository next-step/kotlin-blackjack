package blackjack.domain.player.betting

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ProfitTest : FunSpec({
    test("Profit eqauls and hashcode") {
        val givenAmount = 1000.0

        val givenProfit = Profit(givenAmount)
        val otherProfit = Profit(givenAmount)

        givenProfit shouldBe otherProfit
        givenProfit.hashCode() shouldBe otherProfit.hashCode()
        (givenProfit == otherProfit) shouldBe true
        givenProfit.amount shouldBe givenAmount
    }

    test("Profit::plus 정상 작동") {
        val givenAmount = 1000.0

        val givenProfit = Profit(givenAmount)
        val otherProfit = Profit(givenAmount)

        val addedProfit = givenProfit + otherProfit

        addedProfit.amount shouldBe givenAmount + givenAmount
    }

    test("Profit::unaryPlus 정상 작동") {
        val givenAmount = 1000.0

        val givenProfit = Profit(givenAmount)

        val minusProfit = -givenProfit

        minusProfit.amount shouldBe givenAmount * -1
    }
})
