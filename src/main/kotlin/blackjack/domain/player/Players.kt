package blackjack.domain.player

import blackjack.domain.card.Card
import java.util.Stack

class Players(val players: List<Player>) {
    fun draw(cardDeck: Stack<Card>) = players.forEach { it.draw(cardDeck.pop()) }

    fun draw(cardDeck: Stack<Card>, repeatCount: Int) {
        return players.forEach { player ->
            repeat(repeatCount) { player.draw(cardDeck.pop()) }
        }
    }
}
