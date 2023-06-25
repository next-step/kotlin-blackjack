package blackjack.vo

import blackjack.domain.GameResult

class GameResultVO(
    val dealerWinCount: Int,
    val dealerLoseCount: Int,
    val dealerTieCount: Int,
    val playerGameResultVOs: List<PlayerGameResultVO>,
) {
    companion object {
        fun of(playerGameResults: List<PlayerGameResultVO>) = GameResultVO(
            dealerWinCount = playerGameResults.count { it.gameResult == GameResult.LOSE },
            dealerLoseCount = playerGameResults.count { it.gameResult == GameResult.WIN },
            dealerTieCount = playerGameResults.count { it.gameResult == GameResult.TIE },
            playerGameResultVOs = playerGameResults,
        )
    }
}
