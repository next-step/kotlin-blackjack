package model

class Player(val name: PlayerName) {
    private val mutableCards: MutableList<Card> = mutableListOf()

    val cards: List<Card>
        get() = mutableCards.toList()

    constructor(name: String) : this(PlayerName(name))

    fun receive(card: Card) = mutableCards.add(card)

    fun score(): Int {
        val sum = cards.sumBy { it.denomination.score }
        return if (isAceBonus(sum)) {
            sum + ACE_BONUS
        } else {
            sum
        }
    }

    fun isReceiveCard(isReceive: Boolean): Boolean {
        return isReceive && !isOver()
    }

    fun cardCount(): Int {
        return mutableCards.size
    }

    private fun isAceBonus(sum: Int) = hasAce() && sum <= ACE_BONUS_CONDITION

    private fun hasAce(): Boolean {
        return cards.any { it.denomination.isAce() }
    }

    private fun isOver(): Boolean = score() >= WINNING_POINT

    companion object {
        const val WINNING_POINT = 21
        const val ACE_BONUS = 10
        const val ACE_BONUS_CONDITION = 11
    }
}
