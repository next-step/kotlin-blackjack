package blackjack.deck

import blackjack.entity.Card
import blackjack.exception.BizException
import blackjack.exception.enums.CardDeckError

object CardDeck {

    private val cards: MutableList<Card> = GeneratorCardDeck.generatorCardDeck()

    fun draw(): Card {
        require(cards.isNotEmpty()) { throw BizException(CardDeckError.CARD_EMPTY) }

        val card = cards.random()
        cards.remove(card)

        return card
    }
}
