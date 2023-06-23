package blackjack.domain.player

import blackjack.domain.card.Cards

class Players(val value: List<Player>) {
    fun receiveCard(getCards: () -> Cards) {
        val cards = getCards()
        value.forEach { player -> player.initCards(cards) }
    }

    companion object {
        fun from(vararg player: Player): Players {
            return Players(player.toList())
        }
    }
}
