package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.player.Participant

data class Hands(
    val playerName: String,
    val cards: Set<Card>
) {
    companion object {
        fun from(participant: Participant) = Hands(playerName = participant.name(), cards = participant.cards())
    }
}
