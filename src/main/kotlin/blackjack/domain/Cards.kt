package blackjack.domain

import kotlin.math.abs

class Cards(private val value: ArrayList<Card>) {
    private var sum: Int = 0

    fun drawCard() {
        value.add(CardDeck.drawCard())
    }

    fun calculateCards(): Int {
        sortByAce()
        sum = 0
        for (card in value) {
            val convertResult = convertNumberToInt(card) ?: convertMagicNumber(card)
            sum += convertResult
        }
        return sum
    }

    fun toList(): List<Card> {
        return value.toList()
    }

    private fun sortByAce() {
        value.sortBy { card -> card.value.first == "A" }
    }

    private fun convertNumberToInt(card: Card): Int? {
        return card.value.first.toIntOrNull()
    }

    private fun convertMagicNumber(card: Card): Int {
        if (card.value.first == "A") return selectBestWayOfAce()
        return 10
    }

    private fun selectBestWayOfAce(): Int {
        val whenAceIs11 = abs(WINNING_NUMBER - (sum + 11))
        val whenAceIs1 = abs(WINNING_NUMBER - (sum + 1))

        if (whenAceIs11 < whenAceIs1) {
            return 11
        }
        return 1
    }

    companion object {
        const val WINNING_NUMBER = 21
    }
}
