package blackjack.view.dto

import blackjack.domain.card.Card

data class PlayerDto(
    val name: String,
    val cards: List<Card>,
)
