package model

import blackjack.BlackJackGame
import game.BlackJackWinner

abstract class AbstractPlayer(val name: PlayerName, val playerType: PlayerType) {
    private val _cards: MutableList<Card> = mutableListOf()

    constructor(name: String) : this(PlayerName(name), PlayerType.PLAYER)

    val cards: List<Card>
        get() = _cards.toList()

    fun receive(card: Card) = _cards.add(card)

    fun score(): Int {
        val sum = cards.sumBy { it.denomination.score }
        return if (isAceBonus(sum)) {
            sum + ACE_BONUS
        } else {
            sum
        }
    }

    private fun isAceBonus(sum: Int) = hasAce() && sum <= ACE_BONUS_CONDITION

    private fun hasAce(): Boolean {
        return cards.any { it.denomination.isAce() }
    }

    fun isOver(): Boolean = score() > WINNING_POINT

    fun isReceiveCard(isReceive: Boolean): Boolean {
        return isReceive && !isOver()
    }

    fun cardCount(): Int {
        return _cards.size
    }

    fun isDealer(): Boolean {
        return this.playerType == PlayerType.DEALER
    }

    abstract fun receiveCard(blackJackGame: BlackJackGame)

    abstract fun compareResult(player: AbstractPlayer): BlackJackWinner

    companion object {
        const val WINNING_POINT = 21
        const val ACE_BONUS = 10
        const val ACE_BONUS_CONDITION = 11
    }
}
