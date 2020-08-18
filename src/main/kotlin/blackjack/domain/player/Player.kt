package blackjack.domain.player

import blackjack.domain.deck.Card

data class Player(
    private val id: Int,
    val name: String
) : Participant, Comparable<Player> {
    override val hand = Hand()

    fun hasFreeSpace() = hand.hasFreeSpace()

    fun isWinnerCandidate() = hand.status != HandStatus.BUST

    fun getScore() = hand.calculate()

    override fun toString(): String = "$name : ${getCards().joinToString()}"

    override fun receiveCard(card: Card): HandStatus {
        return hand.addNew(card)
    }

    override fun compareTo(other: Player) = getScore().compareTo(other.getScore())
}
