package next.step.blackjack.domain.dealer

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.Cards

data class Dealer(private val cards: Cards = Cards.of(emptyList())) {

    fun name(): String = DEALER_NAME

    fun cardDescFirst(): String = cards.descFirst()

    fun cardDescs(): Set<String> = cards.descs()

    fun canHit(): Boolean = cards.point() <= HIT_AVAILABLE_POINT

    fun hit(card: Card) {
        cards.add(card)
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val HIT_AVAILABLE_POINT = 16

        fun of(cards: Cards): Dealer = Dealer(cards)
    }
}