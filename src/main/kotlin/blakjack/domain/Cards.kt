package blakjack.domain

@JvmInline
value class Cards(val values: Set<Card>) {
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

    fun hide(): Cards {
        return Cards(values.drop(1).toSet())
    }

    companion object {
        fun empty(): Cards = Cards(emptySet())

        fun all(): Cards {
            return Cards(
                CardSuit.values().flatMap { suit ->
                    CardRank.values().map { rank ->
                        Card(suit, rank)
                    }
                }.toSet()
            )
        }
    }
}
