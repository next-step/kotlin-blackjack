package blackjack

class Cards(
    private val cards: List<Card>
) : List<Card> by cards {
    constructor(vararg cards: Card) : this(cards.map { it })

    fun countAces(): Int {
        return cards.count { it.denomination.isAce() }
    }

    fun calculateScore(): Int {
        var scoreSum = cards.sumOf { it.denomination.maxScore() }
        for (i in 1..countAces()) {
            if (scoreSum <= WINNING_NUMBER) {
                break
            }
            scoreSum = scoreSum - Denomination.ACE.maxScore() + Denomination.ACE.score
        }

        return scoreSum
    }

    operator fun plus(card: Card): Cards {
        return Cards(cards + card)
    }

    companion object {
        const val WINNING_NUMBER = 21
    }
}
