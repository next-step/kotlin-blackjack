package blackjack

import blackjack.state.Ready
import blackjack.state.Stand
import blackjack.state.State

abstract class UserRole(open val name: String, open val state: State = Ready(PlayerDeck())) {
    val cards: List<Card>
        get() = state.currentCard()
            .cards
            .toList()

     open fun draw(card: Card): UserRole {
        return Player(name, state.draw(card))
    }

     open fun stand(): UserRole {
        if (state.isFinish()) {
            return this
        }
        return Player(name, Stand(state.currentCard()))
    }

     fun isFinish(): Boolean = state.isFinish()

     fun getScore(): Int = state.score(state.currentCard().cards)

     open fun isDealer(): Boolean = false
}
