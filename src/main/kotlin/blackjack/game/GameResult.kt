package blackjack.game

import blackjack.participant.BettingAmount
import blackjack.participant.Name

data class GameResult(
    val resultMap: Map<Name, BettingAmount>
)
