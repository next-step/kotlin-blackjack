package blackjack.dto

import blackjack.domain.player.state.role.Role

data class ParticipantDto(val name: String, val cards: List<String>, val score: Int) {
    companion object {
        fun from(role: Role): ParticipantDto {
            return ParticipantDto(role.name.toString(), role.getCardsAsListString(), role.getScore())
        }
    }
}
