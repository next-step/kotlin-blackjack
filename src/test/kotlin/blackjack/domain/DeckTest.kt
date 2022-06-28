package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Face
import blackjack.domain.card.Suit
import io.kotest.assertions.throwables.shouldThrowExactly
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

    test("덱에 카드가 부족할 때, 첫 턴의 카드를 뽑으면 예외를 발생시킨다.") {
        // given
        val deck = Deck(mutableListOf(Card(Suit.DIAMOND, Face.NINE)))

        // when // then
        shouldThrowExactly<IllegalArgumentException> { deck.pullOutFirstTurn() }
    }

    test("덱에 카드가 부족할 때, 카드를 뽑으면 예외를 발생시킨다.") {
        // given
        val deck = Deck(mutableListOf())

        // when // then
        shouldThrowExactly<IllegalArgumentException> { deck.pullOut() }
    }
})
