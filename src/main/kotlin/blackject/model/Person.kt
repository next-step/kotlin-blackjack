package blackject.model

import blackject.model.card.Card
import blackject.model.card.CardNumber
import java.lang.Math.abs

/**
 * 참가자 정보 관리 클래스
 * */
data class Person(
    val name: String,
    private val _cardList: MutableList<Card> = mutableListOf()
) {

    val cardList: List<Card>
        get() = _cardList.toList()

    fun isTakeMoreCard(): Boolean = MAX_TOTAL_NUMBER > getTotalCount(_cardList)

    fun getResultNumber(): Int {
        val minCardNumber = getTotalCount(_cardList)
        val maxCardNumber = getTotalCount(_cardList, true)
        if (!exceedMaximumCardNumber(maxCardNumber) && abs(MAX_TOTAL_NUMBER - maxCardNumber) < abs(MAX_TOTAL_NUMBER - minCardNumber)) {
            return maxCardNumber
        }
        return minCardNumber
    }

    fun addCard(cards: List<Card>) {
        _cardList.addAll(0, cards)
    }

    companion object {
        const val MAX_TOTAL_NUMBER = 21

        fun getTotalCount(list: List<Card>, isMaxNumber: Boolean = false): Int =
            list
                .map { it.number }
                .sumOf { CardNumber.getNumberValue(it, isMaxNumber) }

        fun exceedMaximumCardNumber(sum: Int) = sum > MAX_TOTAL_NUMBER
    }
}
