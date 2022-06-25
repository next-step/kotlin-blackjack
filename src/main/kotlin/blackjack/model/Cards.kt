package blackjack.model

data class Cards(val values: List<Card>) {
    constructor(vararg values: Card) : this(values.asList())

    init {
        require(values.distinct().size == values.size) {
            "카드는 중복을 허용하지 않습니다."
        }
    }

    val scores: List<Score> = if (values.isEmpty()) {
        emptyList()
    } else {
        values
            .map { it.cardNumber.scores }
            .reduce { n1: List<Score>, n2: List<Score> ->
                n1.flatMap { i1 ->
                    n2.map { i2 ->
                        i1 + i2
                    }
                }
            }
    }

    fun pollCards(numOfCards: Int): Pair<List<Card>, Cards> {
        return Pair(values.subList(0, numOfCards), Cards(values.subList(numOfCards, values.size)))
    }

    fun addCards(newCardList: List<Card>): Cards {
        return Cards(values + newCardList)
    }

    fun optimalScore(): Score {
        val notBustScores = scores.filter { !it.isBust() }
        if (notBustScores.isNotEmpty()) {
            return notBustScores.maxByOrNull { it.value }!!
        }

        return scores.minByOrNull { it.value } ?: throw RuntimeException("score는 항상 존재해야 합니다.")
    }

    companion object {
        const val NUMBER_OF_INIT_CARDS = 2
        const val NUMBER_OF_GIVE_CARDS = 1

        fun emptyCards(): Cards {
            return Cards(emptyList())
        }

        fun shuffledCards(): Cards {
            val cardList = CardNumber.values().flatMap { cardNumber ->
                Suit.values().map { suit ->
                    Card(cardNumber, suit)
                }
            }
            return Cards(cardList.shuffled())
        }
    }
}
