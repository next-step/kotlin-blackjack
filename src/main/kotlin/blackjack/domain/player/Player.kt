package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.Finished
import blackjack.domain.state.First
import blackjack.domain.state.State

class Player(val name: Name) {
    var state: State = First(Cards())
    val score: Int
        get() = state.hands.score().toInt()
    val cards: Cards
        get() = state.getCards()

    fun receiveCard(card: Card) {
        state = state.draw(card)
    }

    fun isFinished(): Boolean = state is Finished
}
