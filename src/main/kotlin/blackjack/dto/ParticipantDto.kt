package blackjack.dto

import blackjack.domain.participant.state.role.Role

data class ParticipantDto(val name: String, val cards: List<String>, val score: Int) {
    companion object {
        fun from(role: Role): ParticipantDto {
            return ParticipantDto(role.name.toString(), role.cards, role.score)
        }
    }
}
