package blackjack.domain

open class Player(val playerName: EntrantName, private val hand: Hand = Hand()) : Participant(hand) {
    val name: String
        get() = playerName.value

    fun same(other: String): Boolean {
        return playerName.value == other
    }

    fun matchOf(other: Participant): MatchType {
        return when {
            other.isBust -> MatchType.WIN
            isBust -> MatchType.LOSE
            else -> MatchType.evaluate(hand.bustGap(), other.bustGap)
        }
    }

    companion object {
        fun from(name: String): Player {
            return Player(playerName = EntrantName(name))
        }
    }
}
