package blackjack.view.dto

import blackjack.domain.player.Participant

data class ParticipantDto(
    val name: String,
    val cards: List<CardDto>,
) {
    companion object {
        fun from(participant: Participant): ParticipantDto =
            ParticipantDto(
                name = participant.name,
                cards = participant.cards.getCards().map { card -> CardDto(card.shape.symbol, card.number.value) },
            )
    }
}
