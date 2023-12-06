package blackjack.view.dto

import blackjack.domain.card.Card

data class DealerCardsResultDto(
    val cards: List<Card>,
    val score: Int,
)
