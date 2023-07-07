package blackjack.test

import blackjack.domain.PlayerHand
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape

object FakeGenerator {
    fun card(number: CardNumber): Card {
        return Card(number, CardShape.values().random())
    }

    fun card(): Card {
        return Card(
            CardNumber.values().random(),
            CardShape.values().random()
        )
    }

    fun cards(vararg number: CardNumber): List<Card> {
        return number.toList().map { card(it) }
    }

    fun cardsOfScore22(): List<Card> {
        return listOf(card(CardNumber.TEN), card(CardNumber.TEN), card(CardNumber.TWO))
    }

    fun cardsOfScore21(): List<Card> {
        return listOf(card(CardNumber.TEN), card(CardNumber.NINE), card(CardNumber.TWO))
    }

    fun cardsOfScore20(): List<Card> {
        return listOf(card(CardNumber.TEN), card(CardNumber.TEN))
    }

    fun playerHandOf22(): PlayerHand {
        return PlayerHand.init.add(cardsOfScore22())
    }

    fun playerHandOf21(): PlayerHand {
        return PlayerHand.init.add(cardsOfScore21())
    }

    fun playerHandOf20(): PlayerHand {
        return PlayerHand.init.add(cardsOfScore20())
    }
}
