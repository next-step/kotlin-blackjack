package blackjack.domain.result

import blackjack.domain.participant.Money

data class BlackJackResult(
    val participantResults: List<ParticipantResult>
)

data class ParticipantResult(
    val playerName: String,
    val profit: Money
)
