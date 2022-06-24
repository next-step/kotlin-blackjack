package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DeckTest : FunSpec({
    test("두 장의 카드를 뽑는다.") {
        // given
        val deck = Deck.createOf()

        // when
        val actual = deck.pullOutFirstTurn()

        // then
        actual.size shouldBe 2
    }
})
