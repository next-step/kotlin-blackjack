package blackjack.test

import blackjack.domain.BetAmount
import blackjack.domain.Hand
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.participant.Player

object TestObjectGenerator {
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

    fun handOf22(): Hand {
        return Hand.init.add(cardsOfScore22())
    }

    fun handOf21(): Hand {
        return Hand.init.add(cardsOfScore21())
    }

    fun handOf20(): Hand {
        return Hand.init.add(cardsOfScore20())
    }

    fun player(
        name: String? = null,
        hand: Hand = Hand.init,
        betAmount: BetAmount = BetAmount(0)
    ): Player {
        return Player(
            name = name ?: TestUtils.randomString(3),
            hand = hand,
            betAmount = betAmount
        )
    }
}
