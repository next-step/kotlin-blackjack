package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.card.CardDeck

class Players(val players: List<Player>) {
    fun drawAtFirst(cardDeck: CardDeck) {
        return players.forEach { player ->
            repeat(FIRST_DRAW_COUNT) { player.draw(cardDeck.pop(), DrawDecider.DRAW) }
        }
    }

    fun all(action: (Player) -> Unit) = players.forEach(action)

    companion object {
        private const val FIRST_DRAW_COUNT = 2
    }
}
