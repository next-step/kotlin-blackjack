package blackjack

import blackjack.state.Ready
import blackjack.state.Stand
import blackjack.state.State

class Player(val name: String, val state: State = Ready(PlayerDeck())) {

    val cards: List<Card>
        get() = state.currentCard()
            .cards
            .toList()

    fun draw(card: Card): Player {
        return Player(name, state.draw(card))
    }

    fun stand(): Player {
        if (state.isFinish()) {
            return this
        }
        return Player(name, Stand(state.currentCard()))
    }

    fun isFinish(): Boolean = state.isFinish()

    fun getScore(): Int = state.score(state.currentCard().cards)
}
