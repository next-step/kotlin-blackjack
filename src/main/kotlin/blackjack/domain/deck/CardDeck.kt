package blackjack.domain.deck

import blackjack.domain.entity.Card
import blackjack.domain.exception.BizException
import blackjack.domain.exception.enums.CardDeckError

class CardDeck(
    private val cards: MutableList<Card> = GeneratorCardDeck.generatorCardDeck()
) {

    fun draw(): Card {
        require(cards.isNotEmpty()) { throw BizException(CardDeckError.CARD_EMPTY) }

        val card = cards.random()
        cards.remove(card)

        return card
    }
}
