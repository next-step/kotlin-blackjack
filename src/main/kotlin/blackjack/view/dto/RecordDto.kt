package blackjack.view.dto

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player

data class RecordDto(
    val name: String,
    val profit: Int,
)
