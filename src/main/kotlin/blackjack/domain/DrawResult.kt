package blackjack.domain

import blackjack.domain.card.Card

data class DrawResult(
    val playerName: String,
    val cards: List<Card>
)
