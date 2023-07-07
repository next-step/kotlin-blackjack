package blackjack.fixture

import blackjack.domain.Dealer
import blackjack.domain.card.Card
import blackjack.domain.card.OpenCards

object DealerFixture {
    fun of(vararg cards: Card): Dealer {
        require(cards.size >= 2)

        val dealer = Dealer(EmptyCardDeck())
        val openCards = OpenCards(cards[0], cards[1])
        dealer.open(openCards)

        if (cards.size > 2) {
            val afterOpenCards = cards.toList().subList(2, cards.size)
            afterOpenCards.forEach { dealer.hit(it) }
        }

        dealer.stay()
        return dealer
    }
}
