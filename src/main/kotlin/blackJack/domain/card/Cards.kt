package blackJack.domain.card

import blackJack.domain.card.Signal.Companion.MAX_NUMBER

class Cards(private val cards: List<Card>) : List<Card> by cards {

    operator fun plus(card: Card): Cards {
        checkDuplicate(card)
        return Cards(cards + card)
    }

    operator fun minus(card: Card): Cards {
        return Cards(cards - card)
    }

    private fun checkDuplicate(card: Card) {
        require(card !in cards) { DUPLICATE_ERROR }
    }

    fun sumCards(): Int =
        cards.sortedByDescending { it.denomination.orderBy }
            .fold(0) { acc: Int, card: Card ->
                acc + card.denomination.score(acc)
            }
            .let { sum ->
                if (isAce(sum)) {
                    cards.sumOf { it.denomination.score(sum) }
                } else {
                    sum
                }
            }

    private fun isAce(sum: Int) = sum > MAX_NUMBER && cards.map { it.denomination }.contains(Denomination.ACE)

    fun drawRandomCard() = cards.random()

    companion object {
        fun create(): Cards = Cards(makeCards())

        private fun makeCards(): List<Card> = suits.map { suit ->
            denominations.map { denomination -> Card(suit, denomination) }
        }.flatten()

        private const val DUPLICATE_ERROR = "중복 된 카드가 있습니다."
        private val denominations = Denomination.values()
        private val suits = Suit.values()
    }
}
