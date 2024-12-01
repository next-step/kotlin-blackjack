package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

data class GameResult(
    val dealerResult: DealerGameResult,
    val playersResult: List<PlayerGameResult>,
)

data class DealerGameResult(
    val dealer: Dealer,
    val winCount: Int,
    val loseCount: Int,
)

class DealerGameResultBuilder(
    private val dealer: Dealer,
) {
    private var winCount: Int = 0
    private var loseCount: Int = 0

    fun win() {
        winCount++
    }

    fun lose() {
        loseCount++
    }

    fun build(): DealerGameResult = DealerGameResult(
        dealer = dealer,
        winCount = winCount,
        loseCount = loseCount,
    )
}

data class PlayerGameResult(
    val player: Player,
    val isWin: Boolean,
)
