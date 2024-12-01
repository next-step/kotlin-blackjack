package blackjack.view.dto

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player

data class RecordDto(
    val dealerName: String,
    val records: Map<String, Boolean>,
) {
    companion object {
        fun of(
            dealer: Dealer,
            participants: Participants,
        ): RecordDto {
            val players = participants.participants.filterIsInstance<Player>()
            return RecordDto(
                dealerName = dealer.name,
                records = players.associate { it.name to it.isWin(dealer) },
            )
        }
    }
}
