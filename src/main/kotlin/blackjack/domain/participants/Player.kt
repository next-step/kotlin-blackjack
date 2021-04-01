package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.state.Finished
import blackjack.domain.winning.GameResult

class Player(
    name: String,
    initCards: ArrayList<Card> = arrayListOf<Card>(),
    initMoney: Double
) : Participant(name, initCards) {
    private var money = 0.0

    init {
        this.money = initMoney
    }

    fun getEarnRate(dealer: Dealer): Double {
        val profit = state.profit(money)
        val isWinning = isWinning(dealer)
        return when (isWinning) {
            GameResult.WIN -> {
                profit
            }
            GameResult.LOSE -> {
                -1 * money
            }
            GameResult.DRAW -> {
                money
            }
        }
    }

    fun isWinning(dealer: Dealer): GameResult {
        val ad = state as Finished
        return ad.isWinning(this, dealer)
    }

    override fun checkCardDrawAvailable(): Boolean {
        return !state.isFinished
    }
}
