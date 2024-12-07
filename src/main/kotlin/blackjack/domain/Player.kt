package blackjack.domain

open class Player(val playerName: EntrantName, private val hand: Hand = Hand()) : Participant(hand) {
    val name: String
        get() = playerName.value

    fun same(other: String): Boolean {
        return playerName.value == other
    }

    companion object {
        fun from(name: String): Player {
            return Player(playerName = EntrantName(name))
        }
    }
}
