package blackjack.model.cardset

import blackjack.model.card.Card
import blackjack.model.card.CardShape
import blackjack.model.card.Denomination
import blackjack.utils.times

object ShuffledCardSet {
    private val cardList = (Denomination.values() * CardShape.values()).map { Card(it.first, it.second) }

    fun shuffled(): Cards = Cards(cardList.shuffled())
}
