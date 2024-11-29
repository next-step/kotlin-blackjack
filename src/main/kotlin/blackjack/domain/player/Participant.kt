package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.view.dto.CardDto
import blackjack.view.dto.ParticipantDto

sealed class Participant(
    val name: String,
    val cards: Cards = Cards(),
) {
    fun receiveCard(card: Card) {
        cards.add(card)
    }

    abstract fun canReceive(): Boolean

    companion object {
        fun toDto(participant: Participant): ParticipantDto =
            ParticipantDto(
                name = participant.name,
                cards = participant.cards.getCards().map { card -> CardDto(card.shape.symbol, card.number.value) },
            )
    }
}
