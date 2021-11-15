package blackject.model

import blackject.model.card.Card
import blackject.model.card.CardNumber
import kotlin.math.abs

/**
 * 참가자 정보 관리 클래스
 * */
data class Person(
    val name: String,
    private val _cardList: MutableList<Card> = mutableListOf()
) {

    val cardList: List<Card>
        get() = _cardList.toList()

    private fun isCloseMaxIntMoreThenMinNumber(maxInt: Int, maxCardNumber: Int, minCardNumber: Int): Boolean =
        abs(maxInt - maxCardNumber) < abs(maxInt - minCardNumber)

    fun isTakeMoreCard(maxInt: Int): Boolean = maxInt > getTotalCount(_cardList)

    fun getResultNumber(maxInt: Int): Int {
        val minCardNumber = getTotalCount(_cardList, isMaxNumber = false)
        val maxCardNumber = getTotalCount(_cardList, isMaxNumber = true)
        if (!exceedMaximumCardNumber(maxInt, maxCardNumber) && isCloseMaxIntMoreThenMinNumber(
                maxInt,
                maxCardNumber,
                minCardNumber
            )
        ) {
            return maxCardNumber
        }
        return minCardNumber
    }

    fun addCard(cards: List<Card>) {
        _cardList.addAll(0, cards)
    }

    companion object {

        fun getTotalCount(list: List<Card>, isMaxNumber: Boolean = false): Int =
            list
                .map { it.number }
                .sumOf { CardNumber.getNumberValue(it, isMaxNumber) }

        fun exceedMaximumCardNumber(maxInt: Int, sum: Int) = sum > maxInt
    }
}
