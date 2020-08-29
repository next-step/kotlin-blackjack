package model

import kotlin.math.abs

class Player(val name: PlayerName) {
    private val mutableCards: MutableList<Card> = mutableListOf()

    constructor(name: String) : this(PlayerName(name))
    val cards: List<Card>
        get() = mutableCards.toList()

    fun receive(card: Card) = mutableCards.add(card)

    fun score(): Int {
        val sum = cards.sumBy { it.denomination.score }
        return if (isAceBonus(sum)) {
            sum + ACE_BONUS
        } else {
            sum
        }
    }

    private fun isAceBonus(sum: Int) = hasAce() && sum <= ACE_BONUS_CONDITION

    private fun hasAce(): Boolean {
        return cards.any { it.denomination.isAce() }
    }

    fun isOver(): Boolean = score() >= WINNING_POINT

    fun scoreGap(): Int = abs(WINNING_POINT - score())

    fun isReceiveCard(isReceive: Boolean): Boolean {
        return isReceive && !isOver()
    }

    fun cardCount(): Int {
        return mutableCards.size
    }

    companion object {
        const val WINNING_POINT = 21
        const val ACE_BONUS = 10
        const val ACE_BONUS_CONDITION = 11
    }
}
