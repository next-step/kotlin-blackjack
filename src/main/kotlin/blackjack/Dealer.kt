package blackjack

class Dealer : Gamer("딜러") {
    override val canGetCard: Boolean
        get() = playerCards.getBestScore() < MINIMUM

    companion object {
        const val MINIMUM = 17
    }
}
