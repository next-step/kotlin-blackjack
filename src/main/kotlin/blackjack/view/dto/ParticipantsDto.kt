package blackjack.view.dto

import blackjack.domain.participant.Participants

data class ParticipantsDto(
    val dealer: ParticipantDto,
    val players: List<ParticipantDto>,
) {
    companion object {
        fun from(participants: Participants): ParticipantsDto {
            val dealer = ParticipantDto.from(participants.extractDealer())
            val players = participants.extractPlayers().map { ParticipantDto.from(it) }

            return ParticipantsDto(dealer, players)
        }
    }
}
