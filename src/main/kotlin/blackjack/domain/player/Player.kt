package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand

abstract class Player(name: PlayerName, protected val hand: Hand = Hand(), bet: Bet) {
    var status: Status = Status(name, bet)
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
    fun isBlackJackHand() = hand.isBlackJackHand()

    fun countUpWin(wins: Int = 1) {
        status = Status(status.name, status.gameStatus.bet, WinStatus(status.getWin() + wins, status.getLose()))
    }

    fun countUpLose(lose: Int = 1) {
        status = Status(status.name, status.gameStatus.bet, WinStatus(status.getWin(), status.getLose() + lose))
    }

    fun getWins() = status.getWin()

    fun getLoses() = status.getLose()

    fun getMaxHandValue() = hand.getMaxValue()

    fun getMakeableValues(): List<Int> = hand.getMakeableScores()

    fun isBusted() = hand.isBusted()
}
