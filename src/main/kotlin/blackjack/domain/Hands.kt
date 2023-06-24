package blackjack.domain

import blackjack.domain.card.Card

data class Hands(
    val playerName: String,
    val cards: List<Card>
) {
    companion object {
        fun from(player: Player) = Hands(playerName = player.name, cards = player.cards())
    }
}
