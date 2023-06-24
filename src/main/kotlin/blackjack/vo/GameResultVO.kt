package blackjack.vo

class GameResultVO(
    val dealerWinCount: Int,
    val dealerLoseCount: Int,
    val playerGameResultVOs: List<PlayerGameResultVO>,
) {
    companion object {
        fun of(playerGameResults: List<PlayerGameResultVO>) = GameResultVO(
            dealerWinCount = playerGameResults.count { !it.isWinner },
            dealerLoseCount = playerGameResults.count { it.isWinner },
            playerGameResultVOs = playerGameResults,
        )
    }
}
