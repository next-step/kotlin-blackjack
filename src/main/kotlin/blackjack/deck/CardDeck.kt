package blackjack.deck

import blackjack.entity.Card
import blackjack.entity.enums.Denomination
import blackjack.entity.enums.Suit
import blackjack.exception.BizException
import blackjack.exception.enums.CardDeckError

object CardDeck {

    private val _cards = mutableListOf<Card>()
    val cards get() = _cards

    init {
        Suit
            .values()
            .forEach { suit ->
                Denomination
                    .values()
                    .forEach { denomination ->
                        cards.add(Card(suit, denomination))
                    }
            }

        _cards.shuffle()
    }

    fun draw(): Card {
        require(cards.isNotEmpty()) { throw BizException(CardDeckError.CARD_EMPTY) }

        val card = _cards.random()
        _cards.remove(card)

        return card
    }
}
