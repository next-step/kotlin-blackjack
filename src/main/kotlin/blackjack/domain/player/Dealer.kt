package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardsDeck

class Dealer(
    val dealer: Participant
) : BlackjackFunction by dealer, RevenueFunction by dealer {

    fun addCardWhenLessThanStandard(
        cardsDeck: CardsDeck,
    ): Card? {
        if (dealer.getCardSum() >= STANDARD) {
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
