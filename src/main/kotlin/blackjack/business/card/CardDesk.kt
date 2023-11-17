package blackjack.business.card

import blackjack.business.cardSelectionStrategy.CardSelectionStrategy
import blackjack.business.cardSelectionStrategy.RandomCardSelectionStrategy

class CardDesk(
    private val cardSelectionStrategy: CardSelectionStrategy = RandomCardSelectionStrategy()
) {

    private val _cards = CardFactory.getCards().toMutableList()

    val cards: List<Card>
        get() = _cards.toList()

    fun addCards() {
        _cards.addAll(CardFactory.getCards())
    }

    fun draw(): Card {
        return cardSelectionStrategy.selectCard(_cards).also {
            _cards.remove(it)
            if (_cards.isEmpty()) addCards()
        }
    }

    fun startDraw(): List<Card> = (1..2).map {
        cardSelectionStrategy.selectCard(_cards).also {
            _cards.remove(it)
            if (_cards.isEmpty()) addCards()
        }
    }
}
