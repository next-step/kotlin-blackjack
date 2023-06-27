package blackjack.domain.card

import blackjack.domain.gamer.PlayerName

data class PlayerCards(
    val playerName: PlayerName,
    val cards: List<Card>,
)
