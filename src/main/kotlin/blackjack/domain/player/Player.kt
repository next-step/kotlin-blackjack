package blackjack.domain.player

import blackjack.domain.deck.Card
import blackjack.domain.game.ScoreCalculator

data class Player(
    private val id: Int,
    val name: String
) : Participant, Comparable<Player> {
    override val hand = Hand()

    fun hasFreeSpace() = hand.hasFreeSpace()

    fun isWinnerCandidate() = hand.status != HandStatus.BUST

    fun getScore() = ScoreCalculator.calculate(hand)

    override fun toString(): String = "$name : ${getCards().joinToString()}"

    override fun giveCard(card: Card): HandStatus {
        return hand.addNew(card)
    }

    override fun getCards() = hand.getCards()

    override fun compareTo(other: Player) = getScore().compareTo(other.getScore())
}
