package blackjack.domain.participant

import blackjack.domain.deck.Card
import blackjack.domain.participant.state.Init
import blackjack.domain.participant.state.State

sealed class Participant(
    protected var state: State
) {
    fun receiveInitCards(firstCard: Card, secondCard: Card) {
        this.state = Init.receiveCard(firstCard = firstCard, secondCard = secondCard)
    }

    fun receiveCard(card: Card) {
        this.state = this.state.receiveCard(card = card)
    }

    fun stay() {
        this.state = this.state.stay()
    }

    fun getScore(): Int = this.state.score().value

    fun cards(): List<Card> = this.state.cards()
}
