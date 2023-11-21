package blackjack.view.dto

import blackjack.domain.card.Card

data class FinalPlayerStateDto(
    val name: String,
    val cards: List<Card>,
    val score: Int,
)
