package blackjack.domain

import kotlin.math.abs

class Cards(private val value: ArrayList<Card>) {
    var sum: Int = 0
        private set

    init {
        checkContainDuplicateCard()
    }

    fun drawCard() {
        value.add(CardDeck.drawCard())
    }

    fun getCardList(): List<String> {
        return value.map {
            it.displayName
        }
    }

    fun calculateMyCards(): Int {
        sortByAce()
        sum = 0
        for (card in value) {
            sum += getCardNumber(card)
        }

        return sum
    }

    private fun getCardNumber(card: Card): Int {
        if (card.value.first == Number.ACE) {
            return selectAceIsBetterNumber()
        }
        return card.value.first.score
    }

    private fun sortByAce() {
        value.sortBy { card -> card.value.first == Number.ACE }
    }

    private fun selectAceIsBetterNumber(): Int {
        val whenAceIs11 = abs(WINNING_NUMBER - (sum + 11))
        val whenAceIs1 = abs(WINNING_NUMBER - (sum + 1))

        if (whenAceIs11 < whenAceIs1) {
            return 11
        }
        return 1
    }

    private fun checkContainDuplicateCard() {
        val numberOfDuplicate= value.groupBy { it }
            .values
            .count{ it.size > 1}
        if(numberOfDuplicate > 0) throw IllegalStateException("중복된 카드가 존재할 수 없습니다.")
    }

    companion object {
        const val WINNING_NUMBER = 21
    }
}
