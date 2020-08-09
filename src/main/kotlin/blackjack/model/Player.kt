package blackjack.model

import blackjack.model.card.Cards
import blackjack.model.card.Deck

class Player(
    val name: String
) {
    private val _cards = Cards()
    val cards: Cards
        get() = _cards

    fun gameBatting(cardDeck: Deck) {
        _cards.addCard(cardDeck.popCard())
        _cards.addCard(cardDeck.popCard())
    }

    fun hit(cardDeck: Deck) {
        _cards.addCard(cardDeck.popCard())
    }

    fun getScore() = _cards.getScore()

    companion object {
        fun newInstance(name: String) = Player(name)

        fun newInstances(names: List<String>) = names.map {
            newInstance(it)
        }
    }
}
