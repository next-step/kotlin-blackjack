package blackjack.domain.player

import blackjack.domain.deck.Card

interface Participant {
    val hand: Hand

    fun giveCard(card: Card): HandStatus

    fun getCards() = hand.getCards()
}
