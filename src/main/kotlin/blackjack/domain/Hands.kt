package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.player.Player

data class Hands(
    val playerName: String,
    val cards: List<Card>
) {
    companion object {
        fun from(player: Player) = Hands(playerName = player.name.value, cards = player.cards())
    }
}
