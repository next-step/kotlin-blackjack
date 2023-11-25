package blackjack

class Dealer : Gamer("딜러") {
    override val canGetCard: Boolean
        get() = playerCards.getBestScore() < MINIMUM

    override fun getInitialPublicCards(playerCards: PlayerCards) =
        PlayerCards(listOf(playerCards.cards[1]).toMutableList())

    companion object {
        const val MINIMUM = 17
    }
}
