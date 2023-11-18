package blackjack.view.model

import blackjack.domain.card.Card

data class PlayerModel(
    val name: String,
    val cards: List<Card>,
)
