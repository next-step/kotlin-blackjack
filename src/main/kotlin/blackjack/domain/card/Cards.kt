package blackjack.domain.card

class Cards(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun cardScore(): CardScore {
        return CardScore.of(score(), cards.size)
    }

    fun score(): Score {
        val (aces, normalCards) = cards.partition { it.denomination is Ace }

        val normalScores = normalCards.map { it.denomination.score }
            .fold(Score.ZERO) { acc, score -> acc + score }

        return aces.map { it.denomination as Ace }
            .fold(normalScores) { acc, ace -> acc + aceScore(ace, acc) }
    }

    private fun aceScore(ace: Ace, acc: Score): Score {
        return if (acc + ace.maxScore <= Score.BLACKJACK) {
            ace.maxScore
        } else {
            ace.score
        }
    }

    companion object {
        fun empty(): Cards {
            return Cards(emptyList())
        }
    }
}

fun cards(block: CardsBuilder.() -> Unit): Cards {
    return CardsBuilder().apply(block).build()
}

class CardsBuilder {
    private val cards: MutableList<Card> = mutableListOf()

    fun card(block: CardBuilder.() -> Unit) {
        cards.add(CardBuilder().apply(block).build())
    }

    fun build(): Cards {
        return Cards(cards)
    }
}
