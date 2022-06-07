package blackjack.model.card

class Card private constructor(val denomination: Denomination, val shape: CardShape) {

    val cardId: Int
        get() = cardIdOf(denomination, shape)

    private constructor(cardId: Int) : this(denomination = denominationOf(cardId), shape = shapeOf(cardId))

    companion object {

        private val COUNT_OF_CARD_FULL_SET = Denomination.count * CardShape.count
        val cardList = (0 until COUNT_OF_CARD_FULL_SET).map(::Card)

        fun of(denomination: Denomination, shape: CardShape): Card = cardList[cardIdOf(denomination, shape)]

        fun of(cardId: Int): Card = cardList[cardId]

        private fun cardIdOf(denomination: Denomination, shape: CardShape): Int =
            denomination.ordinal * CardShape.count + shape.ordinal

        private fun denominationOf(cardId: Int) = Denomination.values()[cardId / CardShape.count]
        private fun shapeOf(cardId: Int) = CardShape.values()[cardId % CardShape.count]
    }
}

open class Cards(private val cardList: List<Card> = listOf()) : List<Card> by cardList {

    companion object {

        val shuffled: Cards
            get() = Cards(Card.cardList.shuffled())
    }
}
