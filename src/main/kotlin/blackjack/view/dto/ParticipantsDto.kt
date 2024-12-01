package blackjack.view.dto

import blackjack.domain.player.Participants

data class ParticipantsDto(val players: List<ParticipantDto>) {
    companion object {
        fun from(participants: Participants): ParticipantsDto {
            val playersDto =
                participants.participants.map {
                    ParticipantDto(
                        name = it.name,
                        cards = it.cards.getCards().map { card -> CardDto(card.shape.symbol, card.number.value) },
                    )
                }

            return ParticipantsDto(playersDto)
        }
    }
}
