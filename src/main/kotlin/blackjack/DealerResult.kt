package blackjack

import blackjack.domain.CalculateEarnAmount
import blackjack.domain.PlayerGameResult

class DealerResult(playerGameResults: List<PlayerGameResult>) : CalculateEarnAmount {
    private var earnAmount: Int =
        playerGameResults
            .map { it.getEarnAmount() }
            .fold(0) { total, amount -> total + amount }.toInt()

    override fun getEarnAmount(): Int {
        return -earnAmount
    }
}
