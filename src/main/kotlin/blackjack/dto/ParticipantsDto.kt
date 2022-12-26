package blackjack.dto

import blackjack.domain.participant.Participants

@JvmInline
value class ParticipantsDto(val players: List<ParticipantDto>) {
    fun getNames(): List<String> {
        return players.map { it.name }
    }

    companion object {
        fun from(participants: Participants): ParticipantsDto {
            return ParticipantsDto(participants.getAll().map { ParticipantDto.from(it) })
        }
    }
}
