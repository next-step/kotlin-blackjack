package blackJack.domain.card

import blackJack.domain.card.Denomination.Companion.ACE_BONUS_NUMBER
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

    fun sumCards(): Int {
        val total = cards.sumOf { it.denomination.number }
        return sumBonus(total)
    }

    private fun sumBonus(total: Int): Int =
        if (isAce()) {
            addAceBonus(total)
        } else {
            total
        }

    private fun isAce() = cards.map { it.denomination }.contains(Denomination.ACE)

    private fun addAceBonus(total: Int): Int {
        val aceBonusTotal = total + ACE_BONUS_NUMBER
        return if (aceBonusTotal <= MAX_NUMBER) {
            aceBonusTotal
        } else {
            total
        }
    }

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
