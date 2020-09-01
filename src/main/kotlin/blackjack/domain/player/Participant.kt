package blackjack.domain.player

import blackjack.domain.deck.Card

abstract class Participant : Comparable<Participant> {
    private val hand: Hand = Hand()

    fun getCards() = hand.cards.toList()

    private fun isWinnerCandidate() = hand.status != HandStatus.BUST

    fun hasFreeSpace() = hand.hasFreeSpace()

    fun getScore() = hand.calculate()

    fun receiveCard(card: Card) = hand.addNew(card)

    fun receiveCards(vararg cards: Card): HandStatus {
        cards.forEach { receiveCard(it) }
        return hand.status
    }

    fun findResult(participant: Participant) = when {
        !isWinnerCandidate() -> GameResult.LOSE
        !participant.isWinnerCandidate() -> GameResult.WIN
        participant > this -> GameResult.LOSE
        participant < this -> GameResult.WIN
        else -> GameResult.PUSH
    }

    override fun toString() = "${getCards().joinToString()} (${getScore()})"

    override fun compareTo(other: Participant) = getScore().compareTo(other.getScore())
}
