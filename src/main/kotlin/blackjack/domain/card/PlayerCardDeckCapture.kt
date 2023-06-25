package blackjack.domain.card

import blackjack.domain.player.PlayerName

data class PlayerCardDeckCapture(
    val playerName: PlayerName,
    val cards: List<Card>,
)
