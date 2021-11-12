package blackjack.domain.player

import blackjack.domain.card.Card

data class Player(
    val name: String,
    val cards: List<Card>
)
