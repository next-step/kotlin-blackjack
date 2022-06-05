package blackjack.domain.card

import blackjack.domain.Score

class Cards(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun score(): Score {
        val (aces, normalCards) = cards.partition { it.type is Ace }

        val normalScores = normalCards.map { it.type.score }
            .fold(Score.ZERO) { acc, score -> acc + score }

        return aces.map { it.type as Ace }
            .fold(normalScores) { acc, ace ->
                if (acc + ace.maxScore <= WINNING_SCORE) {
                    acc + ace.maxScore
                } else {
                    acc + ace.score
                }
            }
    }

    companion object {
        private val WINNING_SCORE = Score(21)
    }
}
