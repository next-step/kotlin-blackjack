package blackjack.model

import blackjack.CardHolder

class GameDealer(override val cardHand: CardHand): CardHolder {
    override val name: String = "딜러"

    val isDealerShouldMoreCard: Boolean
        get() = cardHand.totalScore <= DEALER_MUST_MORE_SCORE

    companion object {
        const val DEALER_MUST_MORE_SCORE = 16
    }
}
