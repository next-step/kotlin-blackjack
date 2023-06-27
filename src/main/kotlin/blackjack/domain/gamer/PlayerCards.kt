package blackjack.domain.gamer

import blackjack.domain.card.Card

data class PlayerCards(
    val playerName: PlayerName,
    val cards: List<Card>,
)
