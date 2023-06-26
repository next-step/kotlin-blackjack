package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.score.CardScoreCalculator
import blackjack.domain.score.Score
import java.util.LinkedList

abstract class Gamer {

    private val _cards = LinkedList<Card>()
    val cards: List<Card>
        get() = _cards.toList()
    val cardsSize: Int
        get() = _cards.size

    var score = Score(0)
        private set

    var state = GamerState.WAIT
        private set

    fun pass(newCard: Card) {
        pass(listOf(newCard))
    }

    fun pass(newCards: List<Card>) {
        require(state.canHit()) {
            "current state is $state. card pass is only available in ${GamerState.WAIT} state "
        }

        _cards.addAll(newCards)
        score = CardScoreCalculator.calculateScore(_cards)
        if (score.isBust) {
            state = GamerState.BUST
        }
    }

    fun hasCard(): Boolean {
        return _cards.isNotEmpty()
    }

    fun notHasCard(): Boolean {
        return _cards.isEmpty()
    }

    fun stay() {
        require(state.canToStay()) {
            "current state is $state. stay command is available in ${GamerState.values().filter { it.canToStay() }}"
        }

        state = GamerState.STAY
    }
}
