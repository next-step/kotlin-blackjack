package blackjack.vo

import java.math.BigDecimal

class ProfitResultVO(
    val dealerProfitAmount: BigDecimal,
    val playerProfitVOS: List<PlayerProfitVO>,
) {
    companion object {
        fun of(playerGameResults: List<PlayerProfitVO>) = ProfitResultVO(
            dealerProfitAmount = -playerGameResults.sumOf { it.profitAmount },
            playerProfitVOS = playerGameResults,
        )
    }
}
