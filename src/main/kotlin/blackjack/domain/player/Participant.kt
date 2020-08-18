package blackjack.domain.player

import blackjack.domain.deck.Card

interface Participant {
    val hand: Hand

    fun receiveCard(card: Card): HandStatus

    fun getCards() = hand.cards
}
