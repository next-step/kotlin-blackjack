package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Finished
import blackjack.domain.state.Stay
import blackjack.domain.winning.GameResult
import java.lang.IllegalStateException

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
        return when(state) {
            is Stay -> {
                whenStateIsStayWinning(dealer)
            }
            is Bust -> {
                whenStateIsBustWinning()
            }
            is Blackjack -> {
                whenStateIsBlackjackWinning(dealer)
            }
            else -> {
                throw IllegalStateException("${this.name}의 게임이 아직 끝나지 않았습니다.")
            }
        }
    }

    private fun whenStateIsBustWinning(): GameResult {
        return GameResult.LOSE
    }

    private fun whenStateIsStayWinning(dealer: Dealer): GameResult {
        if (dealer.state is Bust) return GameResult.WIN
        if (this.getScore() < dealer.getScore()) return GameResult.LOSE
        if (this.getScore() > dealer.getScore()) return GameResult.WIN
        return GameResult.DRAW
    }

    private fun whenStateIsBlackjackWinning(dealer: Dealer): GameResult {
        if (dealer.state is Blackjack) return GameResult.DRAW
        return GameResult.WIN
    }


    override fun checkCardDrawAvailable(): Boolean {
        return !state.isFinished
    }
}
