package blackjack.domain.game.result

import blackjack.domain.participant.Participant

class MatchResult(
    val participant: Participant,
    val betResultAmount: Double,
)
