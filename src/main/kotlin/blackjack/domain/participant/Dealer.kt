package blackjack.domain.participant

import blackjack.domain.card.Cards

class Dealer(
    name: String = DEFAULT_NAME,
    cards: Cards = Cards(),
) : Participant(name, cards) {

    override fun canReceiveCard(): Boolean = cards.sum() <= MAX_HIT_CARDS_SUM

    companion object {
        private const val DEFAULT_NAME = "딜러"
        private const val MAX_HIT_CARDS_SUM = 16
    }

}
