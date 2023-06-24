package blackjack.domain

import blackjack.domain.card.Card

class GameResult(
    val playerName: String,
    val cards: List<Card>,
    val score: Int,
)
