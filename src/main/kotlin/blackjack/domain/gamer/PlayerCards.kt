package blackjack.domain.gamer

import blackjack.domain.card.Cards

data class PlayerCards(
    val playerName: PlayerName,
    val cards: Cards,
)
