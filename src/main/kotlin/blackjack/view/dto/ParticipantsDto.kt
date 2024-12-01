package blackjack.view.dto

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player

data class ParticipantsDto(
    val dealer: ParticipantDto,
    val players: List<ParticipantDto>,
) {
    companion object {
        fun from(participants: Participants): ParticipantsDto {
            val dealer = ParticipantDto.from(participants.participants.single { it is Dealer })
            val players = participants.participants.filterIsInstance<Player>().map { ParticipantDto.from(it) }

            return ParticipantsDto(dealer, players)
        }
    }
}
