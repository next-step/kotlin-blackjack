package blackjack.domain

import kotlin.math.abs

class Cards(private val cards: ArrayList<Card>) {
    private var sum: Int = 0

    init {
        checkContainDuplicateCard()
    }

    fun drawCard() {
        cards.add(CardDeck.drawCard())
    }

    fun getCardList(): List<String> {
        return cards.map {
            it.displayName
        }
    }

    fun calculateMyCards(): Int {
        sortByAce()
        sum = 0
        for (card in cards) {
            sum += card.getCardNumber(sum)
        }

        return sum
    }

    private fun sortByAce() {
        cards.sortBy { card -> card.value.first == Number.ACE }
    }

    private fun checkContainDuplicateCard() {
        val numberOfDuplicate = cards.groupBy { it }
            .values
            .count { it.size > 1 }
        if (numberOfDuplicate > 0) throw IllegalStateException("중복된 카드가 존재할 수 없습니다.")
    }

    companion object {
        const val WINNING_NUMBER = 21
    }
}
