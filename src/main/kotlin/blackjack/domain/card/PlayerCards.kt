package blackjack.domain.card

import blackjack.domain.player.PlayerName

data class PlayerCards(
    val playerName: PlayerName,
    val cards: List<Card>,
)
