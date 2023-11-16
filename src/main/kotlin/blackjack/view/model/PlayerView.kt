package blackjack.view.model

import blackjack.domain.card.Card

data class PlayerView(
    val name: String,
    val cards: List<Card>,
)
