package blackjack.domain.card

import blackjack.domain.Score

class Cards(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun score(): Score {
        val (aces, normalCards) = cards.partition { it.denomination is Ace }

        val normalScores = normalCards.map { it.denomination.score }
            .fold(Score.ZERO) { acc, score -> acc + score }

        return aces.map { it.denomination as Ace }
            .fold(normalScores) { acc, ace ->
                if (acc + ace.maxScore <= WINNING_SCORE) {
                    acc + ace.maxScore
                } else {
                    acc + ace.score
                }
            }
    }

    companion object {
        val WINNING_SCORE = Score(21)

        fun empty(): Cards {
            return Cards(emptyList())
        }
    }
}
