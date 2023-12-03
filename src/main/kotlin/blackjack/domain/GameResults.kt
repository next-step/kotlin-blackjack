package blackjack.domain

import java.util.EnumMap

data class GameResults(
    val playerGameResult: List<PlayerGameResult>,
    val dealerGameResult: EnumMap<GameResult, Int>
)
