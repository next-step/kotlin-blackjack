package blackjack.domain

import kotlin.math.ceil
import kotlin.math.min

data class Hands(val cards: List<Card> = listOf()) {

    operator fun plus(card: Card): Hands {
        return Hands(cards + card)
    }

    val size = cards.size
    val sum = cards.sumBy {
        it.value
    }.cutOffWithAces()

    private fun Int.cutOffWithAces(): Int {
        return this - (deducibleAces() * 10)
    }

    /**
     * 합계가 21을 초과했을 때 ACE가 충분히 있다면
     * ACE로 빼서 만들어낼 수 있는 숫자의 영역을 부등호로 표현하면
     * 21 >= sum - 10 * Ace장수 >= 0 가 되므로
     * (합계 - 21) / 10을 한 값의 올림한 값의 장수 만큼 ACE를 11대신 1로 취급할 수 있다.
     */
    private fun Int.deducibleAces() = min(
        ceil((this - BLACK_JACK).toFloat() / 10.0f).toInt(),
        aces()
    )

    private fun aces(): Int {
        return cards.count { it.isAce() }
    }

    fun isBusted(): Boolean {
        return sum > BLACK_JACK
    }
}
