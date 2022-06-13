package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class CardsTest : FunSpec({
    test("카드를 추가할 수 있다.") {
        // given
        val card = Card(CardNumber.ACE, CardSuit.SPADE)
        val sut = Cards()

        // when
        val result: Cards = sut.add(card)

        // then
        result.size() shouldBe 1
    }
})
