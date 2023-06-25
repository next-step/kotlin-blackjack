package blackjack.domain

import blackjack.support.sumOf

class Cards(
    private val cards: List<Card>
) : List<Card> by cards {
    constructor(vararg cards: Card) : this(cards.map { it })

    fun countAces(): Int {
        return cards.count { it.denomination.isAce() }
    }

    fun calculateScore(): Score {
        var scoreSum: Score = cards.sumOf { it.denomination.maxScore() }
        for (i in 1..countAces()) {
            if (!scoreSum.isBurst()) {
                break
            }
            scoreSum = scoreSum + Denomination.ACE.score() - Denomination.ACE.maxScore()
        }

        return scoreSum
    }

    operator fun plus(card: Card): Cards {
        return Cards(cards + card)
    }
}
