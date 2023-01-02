package blackjack.domain.player.betting

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BettingTest : FunSpec({
    test("Betting eqauls and hashcode") {
        val givenAmount = 1000.0

        val givenBetting = Betting(givenAmount)
        val otherBetting = Betting(givenAmount)

        givenBetting shouldBe otherBetting
        givenBetting.hashCode() shouldBe otherBetting.hashCode()
        (givenBetting == otherBetting) shouldBe true
        givenBetting.amount shouldBe givenAmount
    }
})
