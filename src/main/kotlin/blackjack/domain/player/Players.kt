package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.card.Card
import java.util.Stack

class Players(val players: List<Player>) {
    fun drawAtFirst(cardDeck: Stack<Card>) {
        return players.forEach { player ->
            repeat(FIRST_DRAW_COUNT) { player.draw(cardDeck.pop(), DrawDecider.DRAW) }
        }
    }

    companion object {
        private const val FIRST_DRAW_COUNT = 2
    }
}
