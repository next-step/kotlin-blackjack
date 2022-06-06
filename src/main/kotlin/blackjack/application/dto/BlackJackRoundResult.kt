package blackjack.application.dto

import blackjack.domain.RoundResults
import blackjack.domain.player.vo.Name

data class BlackJackRoundResult(
    val name: Name,
    val roundResults: RoundResults
)

data class BlackJackRoundResults(
    val blackJackRoundResults: List<BlackJackRoundResult>
)
