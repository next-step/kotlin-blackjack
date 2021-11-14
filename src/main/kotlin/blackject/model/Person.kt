package blackject.model

import blackject.model.card.Card
import blackject.model.card.CardNumber
import java.lang.Math.abs

/**
 * 참가자 정보 관리 클래스
 * */
data class Person(
    val name: String,
    val cardList: MutableList<Card> = mutableListOf()
) {

    fun isTakeMoreCard(): Boolean = MAX_TOTAL_NUMBER > getTotalCount(cardList)

    fun getResultNumber(): Int {
        val minCardNumber = getTotalCount(cardList)
        val maxCardNumber = getTotalCount(cardList, true)
        if (!exceedMaximumCardNumber(maxCardNumber) && abs(MAX_TOTAL_NUMBER - maxCardNumber) < abs(MAX_TOTAL_NUMBER - minCardNumber)) {
            return maxCardNumber
        }
        return minCardNumber
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
