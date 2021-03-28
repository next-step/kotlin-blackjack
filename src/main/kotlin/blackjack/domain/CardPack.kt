package blackjack.domain

import blackjack.domain.card.CardFactory
import blackjack.domain.card.DefaultCardFactory

class CardPack(cardFactory: CardFactory = DefaultCardFactory()) {
    private val cards = cardFactory.createCards().toMutableList()

    fun poll(): Card {

        check(cards.isNotEmpty()) { "모든 카드가 사용되었습니다." }

        return cards.removeAt(0)
    }
}
