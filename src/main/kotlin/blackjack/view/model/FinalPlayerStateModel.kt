package blackjack.view.model

import blackjack.domain.card.Card

data class FinalPlayerStateModel(
    val name: String,
    val cards: List<Card>,
    val score: Int,
)
