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
