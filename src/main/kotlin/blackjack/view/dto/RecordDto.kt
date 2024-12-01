package blackjack.view.dto

import blackjack.domain.player.Dealer
import blackjack.domain.player.Participants
import blackjack.domain.player.Player

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
