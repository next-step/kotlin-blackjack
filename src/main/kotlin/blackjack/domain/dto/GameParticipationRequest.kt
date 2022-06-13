package blackjack.domain.dto

import blackjack.domain.Bet

data class GameParticipationRequest(
    val name: String,
    val bet: Bet,
)
