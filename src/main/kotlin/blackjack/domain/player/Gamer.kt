package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.score.CardScoreCalculator
import java.util.LinkedList

abstract class Gamer {

    private val _cards = LinkedList<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun pass(newCard: Card) {
        _cards.add(newCard)
    }

    fun pass(newCards: List<Card>) {
        _cards.addAll(newCards)
    }

    fun hasCard(): Boolean {
        return _cards.isNotEmpty()
    }

    fun notHasCard(): Boolean {
        return _cards.isEmpty()
    }

    fun isBust(): Boolean {
        return CardScoreCalculator.calculateScore(_cards).isBust
    }
}
