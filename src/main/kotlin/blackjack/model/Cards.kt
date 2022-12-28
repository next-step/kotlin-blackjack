package blackjack.model

import blackjack.model.Player.Companion.STOP_SCORE

class Cards(
    private val value: List<Card> = listOf()
) : List<Card> by value {
    fun getScore(): Int? {
        return calculate(value)
    }

    private fun calculate(cards: List<Card>, index: Int = 0, accumulator: Int = 0): Int? {
        if (accumulator > STOP_SCORE) {
            return null
        }
        if (index == cards.size) {
            return accumulator
        }

        return handleDfsBranch(cards, index, accumulator)
    }

    private fun handleDfsBranch(cards: List<Card>, index: Int, accumulator: Int): Int? {
        if (!cards[index].isAce()) {
            return calculate(cards, index + INDEX_INCREMENT, accumulator + cards[index].getScore())
        }

        return listOfNotNull(
            calculate(cards, index + INDEX_INCREMENT, accumulator + cards[index].getScore()),
            calculate(cards, index + INDEX_INCREMENT, accumulator + ACE_SPECIAL_SCORE)
        ).maxOrNull()
    }

    companion object {
        private const val INDEX_INCREMENT = 1
        private const val ACE_SPECIAL_SCORE = 11

        fun of(vararg card: Card): Cards {
            return Cards(card.toList())
        }
    }
}
