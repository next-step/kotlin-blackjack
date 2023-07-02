package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant

data class HandsDashboard(
    val dealerHands: DealerHands,
    val playerHands: List<PlayerHands>
)

data class DealerHands(
    val cards: Set<Card>,
) {
    companion object {
        fun from(dealer: Dealer) = DealerHands(cards = dealer.cards())
    }
}

data class PlayerHands(
    val name: String,
    val cards: Set<Card>
) {
    companion object {
        fun from(participant: Participant) = PlayerHands(name = participant.name(), cards = participant.cards())
    }
}
