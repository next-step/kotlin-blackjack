package blackjack.domain.participant

import blackjack.domain.card.Cards

class Player(
    name: String,
    cards: Cards = Cards(),
) : Participant(name, cards) {

    override fun canReceiveCard(): Boolean = cards.sum() < BLACKJACK_SUM

    companion object {
    private const val BLACKJACK_SUM = 21
}

}
