package blackjack.business.card

import blackjack.business.cardSelectionStrategy.CardSelectionStrategy
import blackjack.business.cardSelectionStrategy.RandomCardSelectionStrategy

class CardDesk(
    private val cardFactory: CardFactory = DefaultCardFactory(),
    private val cardSelectionStrategy: CardSelectionStrategy = RandomCardSelectionStrategy()
) {

    private val _cards = cardFactory.getCards().toMutableList()

    val cards: List<Card>
        get() = _cards.toList()

    fun draw(): Card = cardSelectionStrategy.selectCard(_cards).also {
        _cards.remove(it)
        if (_cards.isEmpty()) addAllNewCards()
    }

    fun startDraw(): List<Card> = (1..2).map {
        cardSelectionStrategy.selectCard(_cards).also {
            _cards.remove(it)
            if (_cards.isEmpty()) addAllNewCards()
        }
    }

    private fun addAllNewCards() {
        _cards.addAll(cardFactory.getCards())
    }
}
