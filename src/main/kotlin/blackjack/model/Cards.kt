package blackjack.model

class Cards(val values: List<Card>) {
    constructor(vararg values: Card) : this(values.asList())

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cards

        if (values != other.values) return false
        if (scores != other.scores) return false

        return true
    }

    override fun hashCode(): Int {
        var result = values.hashCode()
        result = 31 * result + scores.hashCode()
        return result
    }

    fun optimalScore(): Score {
        val notLoseScores = scores.filter { !it.isLose() }
        if (notLoseScores.isNotEmpty()) {
            return notLoseScores.maxByOrNull { it.value }!!
        }
        return scores.minByOrNull { it.value }!!
    }

    init {
        require(values.distinct().size == values.size) {
            "카드는 중복을 허용하지 않습니다."
        }
    }

    companion object {
        fun emptyCards(): Cards {
            return Cards(emptyList())
        }
    }
}
