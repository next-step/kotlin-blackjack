package blackjack.domain.participant.state

import blackjack.domain.deck.Card

sealed class Finished(hand: Hand) : AfterInit(hand) {

    override fun receiveCard(card: Card): State = throw IllegalStateException()

    override fun stay(): State = throw IllegalStateException()

    override fun isFinished(): Boolean = true
}
