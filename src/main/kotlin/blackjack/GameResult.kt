package blackjack

import blackjack.domain.PlayerGameResult

data class GameResult(
    val dealerResult: DealerResult,
    val playerGameResults: List<PlayerGameResult>,
)
