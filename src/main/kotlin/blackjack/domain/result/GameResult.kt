package blackjack.domain.result

import blackjack.domain.participant.Player

data class GameResult(
    val dealerName: String,
    val playersResult: List<PlayerGameResult>,
) {
    val dealerProfit: Int = playersResult.sumOf { -it.profit }
}

data class PlayerGameResult(
    val player: Player,
    val resultType: GameResultType,
) {
    val profit: Int = when (resultType) {
        GameResultType.BLACKJACK_WIN -> (player.bettingAmount * 1.5).toInt()
        GameResultType.WIN -> player.bettingAmount
        GameResultType.LOSE -> -player.bettingAmount
        GameResultType.PUSH -> 0
    }
}
