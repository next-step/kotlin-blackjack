package blackjack.domain

import blackjack.enums.Type
import blackjack.enums.Value

data class Cards(private val elements: List<Card>) {
    val size = elements.size

    infix operator fun plus(cards: Cards): Cards {
        return Cards(elements + cards.elements)
    }

    fun take(count: Int): Cards {
        return Cards(elements.take(count))
    }

    fun removeAll(cards: Cards): Cards {
        return Cards(elements.filterNot { cards.elements.contains(it) })
    }

    fun score(): Score {
        val originScore = elements.fold(0) { acc, card -> acc + card.origin }
        val alternativeScore = elements.fold(0) { acc, card ->
            if (card.alternative != 0) {
                acc + card.alternative
            } else {
                acc + card.origin
            }
        }

        return Score(originScore, alternativeScore)
    }

    companion object {
        private val types = Type.values()
        private val values = Value.values()
        private val cards = (0..51).map { Card(types[it / 13], values[it % 13]) }

        fun shuffled(): Cards = Cards(cards.shuffled())
    }
}
