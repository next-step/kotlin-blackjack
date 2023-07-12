package blackjack.test

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.hand.Hand
import blackjack.domain.participant.BetAmount
import blackjack.domain.participant.Player

object TestObjectGenerator {
    fun card(number: CardNumber? = null): Card {
        return Card(
            number = number ?: CardNumber.values().random(),
            shape = CardShape.values().random()
        )
    }

    fun cards(vararg number: CardNumber): List<Card> {
        return number.toList().map { card(it) }
    }

    fun handOf21(): Hand {
        val cards = listOf(card(CardNumber.TEN), card(CardNumber.NINE), card(CardNumber.TWO))
        return Hand.init.add(cards)
    }

    fun handOf20(): Hand {
        val cards = listOf(card(CardNumber.TEN), card(CardNumber.TEN))
        return Hand.init.add(cards)
    }

    fun handOf19(): Hand {
        val cards = listOf(card(CardNumber.TEN), card(CardNumber.NINE))
        return Hand.init.add(cards)
    }

    fun handOfBlackjack(): Hand {
        val cards = listOf(card(CardNumber.ACE), card(CardNumber.TEN))
        return Hand.init.add(cards)
    }

    fun handOfBust(): Hand {
        val cards = listOf(card(CardNumber.TEN), card(CardNumber.TEN), card(CardNumber.TWO))
        return Hand.init.add(cards)
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
