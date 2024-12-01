package blackjack.domain.participant

import blackjack.domain.card.Cards

class Dealer(
    name: String = DEFAULT_NAME,
    cards: Cards = Cards(),
) : Participant(name, cards) {

    companion object {
        private const val DEFAULT_NAME = "딜러"
    }

}
