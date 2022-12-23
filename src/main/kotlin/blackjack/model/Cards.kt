package blackjack.model

import kotlin.math.max

private const val FAIL_SCORE = 0
private const val INDEX_INCREMENT = 1

class Cards(private val value: MutableList<Card> = mutableListOf()) : List<Card> by value {
    fun add(card: Card) {
        value.add(card)
    }

    fun getPickableScore(): Int {
        return value.sumOf { it.getScore() }
    }

    fun getFinalScore(): Int {
        return calculate(value)
    }

    companion object {
        fun of(vararg card: Card): Cards {
            return Cards(card.toMutableList())
        }
    }
}

private fun calculate(cards: List<Card>, index: Int = 0, accumulator: Int = 0): Int {
    if (accumulator > Player.BLACKJACK_SCORE) {
        return FAIL_SCORE
    }
    if (index == cards.size) {
        return accumulator
    }

    return handleDfsBranch(cards, index, accumulator)
}

private fun handleDfsBranch(cards: List<Card>, index: Int, accumulator: Int): Int {
    if (!cards[index].isAce()) {
        return calculate(cards, index + INDEX_INCREMENT, accumulator + cards[index].getScore())
    }

    return max(
        calculate(cards, index + INDEX_INCREMENT, accumulator + Denomination.ACE.score),
        calculate(cards, index + INDEX_INCREMENT, accumulator + Denomination.ACE.specialScore!!)
    )
}
