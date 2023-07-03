package blackjack.domain

import blackjack.domain.card.Card

data class Player(val name: String, val state: PlayerState = PlayerState.init) {
    fun canAddCard(): Boolean = state.canAddCard()

    fun addCard(card: Card): Player {
        val newState = this.state.add(card)
        return copy(state = newState)
    }

    fun addCards(cards: List<Card>): Player {
        val newState = this.state.add(cards)
        return copy(state = newState)
    }

    fun cards() = state.cards

    fun score() = state.score
}
