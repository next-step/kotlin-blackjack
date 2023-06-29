package blackjack.domain.gamer

import blackjack.domain.card.Cards

data class PlayerCards(
    val playerName: String,
    val cards: Cards,
)
