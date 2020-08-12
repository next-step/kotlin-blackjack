package blackjack

class Card {
    private val shape: Symbol = getShape()
    private val number: Numbers = getNumber()

    private fun getShape(): Symbol {
        return Symbol.values().map { it }.shuffled()[0]
    }

    private fun getNumber(): Numbers {
        return Numbers.values().map { it }.shuffled()[0]
    }

    override fun toString(): String {
        return "${ number.shape }${ shape.symbol }"
    }
}
