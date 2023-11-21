package blackjack.domain

class OnGoingPlayer(
    override val name: String,
    override val cards: Cards,
) : Player {
    companion object {
        fun of(name: String, cards: Cards): Player {
            val hardAcePoint = cards.getPoints()

            return if (hardAcePoint == BlackJack.BlackJackedNumber) {
                BlackJackedPlayer(name, cards)
            } else if (hardAcePoint > BlackJack.BlackJackedNumber) {
                BustedPlayer(name, cards)
            } else {
                OnGoingPlayer(name, cards)
            }
        }
    }
}
