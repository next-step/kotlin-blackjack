package blackjack.domain.player

import blackjack.domain.deck.Card

abstract class Participant : Comparable<Participant> {
    protected val hand: Hand = Hand()

    fun getCards() = hand.cards.toList()

    fun hasFreeSpace() = hand.hasFreeSpace()

    fun getScore() = hand.calculate()

    fun receiveCard(card: Card) = hand.addNew(card)

    override fun compareTo(other: Participant) = getScore().compareTo(other.getScore())
}
