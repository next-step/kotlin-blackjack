package blackjack.domain.player

import blackjack.domain.deck.Card

abstract class Participant : Comparable<Participant> {
    private val hand: Hand = Hand()

    fun getCards() = hand.cards.toList()

    fun isWinnerCandidate() = hand.status != HandStatus.BUST

    fun hasFreeSpace() = hand.hasFreeSpace()

    fun getScore() = hand.calculate()

    fun receiveCard(card: Card) = hand.addNew(card)

    override fun toString() = "${getCards().joinToString()} (${getScore()})"

    override fun compareTo(other: Participant) = getScore().compareTo(other.getScore())
}
