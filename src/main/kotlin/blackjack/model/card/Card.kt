package blackjack.model.card

class Card private constructor(val denomination: Denomination, val shape: CardShape) {

    val cardId: Int
        get() = cardIdOf(denomination, shape)

    private constructor(cardId: Int) : this(denomination = denominationOf(cardId), shape = shapeOf(cardId))

    companion object {

        private val COUNT_OF_CARD_FULL_SET = Denomination.count * CardShape.count
        val cardList = (0 until COUNT_OF_CARD_FULL_SET).map { Card(cardId = it) }
        fun of(denomination: Denomination, shape: CardShape): Card = cardList[cardIdOf(denomination, shape)]

        fun of(cardId: Int): Card = cardList[cardId]

        private fun cardIdOf(denomination: Denomination, shape: CardShape): Int =
            denomination.ordinal * CardShape.count + shape.ordinal

        private fun denominationOf(cardId: Int) = Denomination.values()[cardId / CardShape.count]
        private fun shapeOf(cardId: Int) = CardShape.values()[cardId % CardShape.count]
    }
}

open class Cards(private val cardList: List<Card> = listOf()) : List<Card> by cardList {

    val scoreList: List<Int>
        get() {
            val basicScore = this.cardList.sumOf { it.denomination.score }
            val countOfAce = this.cardList.count { it.denomination == Denomination.ACE }
            return (0..countOfAce).map { it * 10 + basicScore }.sorted()
        }

    val resultScore: Int
        get() = when {
            this.isBlackJack -> BLACK_JACK_SCORE
            this.isBust -> this.minScore
            else -> this.maxScoreNotBust
        }

    val minScore: Int
        get() = this.scoreList.minOrNull() ?: 0

    val maxCore: Int
        get() = this.scoreList.maxOrNull() ?: 0

    val maxScoreNotBust: Int
        get() = this.scoreList.filter { it <= BLACK_JACK_SCORE }.maxOrNull() ?: 0

    val isBlackJack: Boolean
        get() = this.scoreList.any { it == BLACK_JACK_SCORE }

    val isBust: Boolean
        get() = this.minScore > BLACK_JACK_SCORE

    val isBustOrBlackJack: Boolean
        get() = this.isBust || this.isBlackJack

    companion object {

        val shuffled: Cards
            get() = Cards(Card.cardList.shuffled())

        private const val BLACK_JACK_SCORE = 21
    }
}
