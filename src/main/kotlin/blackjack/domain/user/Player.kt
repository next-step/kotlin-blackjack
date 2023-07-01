package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.status.ConditionalEndStatus
import blackjack.domain.status.EndStatus
import blackjack.domain.status.FixedEndStatus
import blackjack.domain.status.PlayingStatus
import blackjack.domain.status.Status

open class Player(val name: String) {
    private val cards: MutableList<Card> = mutableListOf()
    private var status: Status = PlayingStatus.READY

    fun getCards(): List<Card> = cards.toList()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun chooseHitOrStay(answer: String) {
        if (answer == WANT_STAY) {
            status = FixedEndStatus.STAY
            return
        }
        status = PlayingStatus.HIT
    }

    fun updateStatus() {
        if (isDone()) return

        val pointResult = calculateMinAndMaxPoint()
        status = ConditionalEndStatus.values()
            .firstOrNull { it.isMatch(pointResult) }
            ?: PlayingStatus.READY
    }

    private fun calculateMinAndMaxPoint(): PointResult {
        val minPoint = cards.sumOf { card -> card.number.value }
        val maxPoint = minPoint + if (hasAce()) ACE_BONUS_POINT else 0

        return PointResult(minPoint, maxPoint)
    }

    private fun hasAce(): Boolean =
        cards.any { card -> card.number == CardNumber.A && card.pattern == CardPattern.Spade }

    fun isDone(): Boolean = status is EndStatus
    fun wantHit(): Boolean = status == PlayingStatus.HIT

    fun getPoint(): Int {
        val pointResult = calculateMinAndMaxPoint()

        return if (pointResult.min >= BLACK_JACK_POINT) pointResult.min
        else pointResult.max
    }

    data class PointResult(val min: Int, val max: Int)

    companion object {
        private const val BLACK_JACK_POINT = 21
        private const val ACE_BONUS_POINT = 9
        const val WANT_STAY = "n"
        const val WANT_HIT = "y"
    }
}
