package blackjack.domain

@JvmInline
value class PlayerCard(val cards: MutableList<Card> = mutableListOf()) {

    fun init(trumpCard: TrumpCard) {
        cards.addAll(mutableListOf(trumpCard.draw(), trumpCard.draw()))
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun score(): Score {
        val first = Score(cards.sumOf { it.calculateScore().first })
        val second = Score(cards.sumOf { it.calculateScore().second })
        if (first.isBurst().not() && second.isBurst().not()) {
            return Score(maxOf(first.score, second.score))
        }
        return Score(minOf(first.score, second.score))
    }
}
