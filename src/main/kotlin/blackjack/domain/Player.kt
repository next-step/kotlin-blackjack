package blackjack.domain

open class Player(val playerName: EntrantName, private val hand: Hand = Hand()) {
    val isBust: Boolean
        get() = hand.isBust
    val totalCards: Deck
        get() = Deck(hand.totalCards)
    val name: String
        get() = playerName.value

    fun receive(cards: Deck) {
        hand.add(cards.values())
    }

    fun score(): Int {
        return hand.score
    }

    fun same(other: String): Boolean {
        return playerName.value == other
    }

    fun isWin(dealer: Dealer): MatchType {
        return when {
            isBust -> MatchType.LOSS
            dealer.isBust -> MatchType.WIN
            score() > dealer.score() -> MatchType.WIN
            score() < dealer.score() -> MatchType.LOSS
            else -> MatchType.DRAW
        }
    }

    companion object {
        fun from(name: String): Player {
            return Player(playerName = EntrantName(name))
        }
    }
}
