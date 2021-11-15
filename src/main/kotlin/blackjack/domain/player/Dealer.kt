package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardsDeck

class Dealer : Participant("딜러") {

    fun addCardWhenLessThanStandard(
        cardsDeck: CardsDeck,
    ): Card? {
        if (getCardSum() >= STANDARD) {
            return null
        }

        val card = cardsDeck.divide()
        addCard(card)

        return card
    }

    companion object {
        private const val STANDARD = 17
    }
}
