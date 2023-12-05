package blackjack.domain

import java.util.EnumMap

data class GameResults(
    val playerGameResults: List<PlayerGameResult>,
    val dealerGameResult: EnumMap<GameResult, Int>
)
