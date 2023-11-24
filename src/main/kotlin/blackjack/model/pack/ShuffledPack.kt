package blackjack.model.pack

import blackjack.model.card.Card
import blackjack.model.card.CardRank
import blackjack.model.card.Suit
import kotlin.random.Random

object ShuffledPack : Pack {
    private val random = Random(System.currentTimeMillis())

    override fun pickCard(): Card {
        return Card.of(randomSuit(), randomCardRank())
    }

    private fun randomCardRank(): CardRank {
        return CardRank.values()[randomInRange(CardRank.values().size)]
    }

    private fun randomSuit(): Suit {
        return Suit.values()[randomInRange(Suit.values().size)]
    }

    private fun randomInRange(range: Int): Int {
        return this.random.nextInt(0, Int.MAX_VALUE) % range
    }
}
