package blackjack

import blackjack.state.Ready
import blackjack.state.Stand
import blackjack.state.State

class Player(val name: String, private val state: State = Ready(PlayerDeck())) {

    val cards: List<Card>
        get() = state.currentCard()
            .cards
            .toList()

    fun draw(card: Card): Player {
        return Player(name, state.draw(card))
    }

    fun stand(): Player {
        return Player(name, Stand(state.currentCard()))
    }
}
