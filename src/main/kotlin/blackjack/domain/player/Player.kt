package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand

abstract class Player(name: PlayerName, protected val hand: Hand = Hand()) {
    var status: Status = Status(name)
        private set

    abstract fun addCardToHand(card: Card): Boolean

    abstract fun isHandAddable(): Boolean

    fun getName() = status.name

    fun getCards() = hand.getCardListInHand()

    private fun isWin(dealerHandValue: Int, dealerBust: Boolean): Boolean {
        if (isBusted())
            return false
        if (dealerBust)
            return true
        return dealerHandValue < (getMakeableValues().maxOrNull() ?: getMaxHandValue())
    }

    fun setResultByDealerScore(dealerHandValue: Int, dealerBust: Boolean): Boolean {
        val isWin = isWin(dealerHandValue, dealerBust)
        if (isWin) {
            countUpWin()
            return isWin
        }
        countUpLose()
        return isWin
    }

    fun countUpWin(wins: Int = 1) {
        status = Status(status.name, WinStatus(status.winStatus.win + wins, status.winStatus.lose))
    }

    fun countUpLose(lose: Int = 1) {
        status = Status(status.name, WinStatus(status.winStatus.win, status.winStatus.lose + lose))
    }

    fun getWins() = status.winStatus.win

    fun getLoses() = status.winStatus.lose

    fun getMaxHandValue() = hand.getMaxValue()

    fun getMakeableValues(): List<Int> = hand.getMakeableScores()

    fun isBusted() = hand.isBusted()
}
