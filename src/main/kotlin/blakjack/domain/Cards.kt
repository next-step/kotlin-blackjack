package blakjack.domain

class Cards(val values: Set<Card>) {
    val size: Int
        get() = values.size

    fun add(card: Card): Cards {
        return Cards(values + card)
    }

    fun add(cards: Cards): Cards {
        return Cards(values + cards.values)
    }

    fun score(): Int {
        return CardsScoreCalculator.sum(this)
    }

    fun scoreWithAceAsOne(): Int {
        return CardsScoreCalculator.sumWithAceAsOne(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cards) return false

        return values == other.values
    }

    override fun hashCode(): Int {
        return values.hashCode()
    }

    companion object {
        fun empty(): Cards = Cards(emptySet())
    }
}
