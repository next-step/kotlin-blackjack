package blackjack.dto

import blackjack.domain.participant.Participants

@JvmInline
value class ParticipantsDto(val participants: List<ParticipantDto>) {
    fun getNames(): List<String> {
        return participants.map { it.name }
    }

    companion object {
        fun from(participants: Participants): ParticipantsDto {
            return ParticipantsDto(participants.getAll().map { ParticipantDto.from(it) })
        }
    }
}
