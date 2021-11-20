package blackjack.model

class Player private constructor(name: Name, cards: Cards) : Gamer(name, cards) {

    override fun copy(name: Name, cards: Cards): Player = from(name, cards)

    override fun canReceive(): Boolean = true

    fun result(dealerScore: Int): Result = when {
        score > TWENTY_ONE -> Result.LOSE
        dealerScore > TWENTY_ONE -> Result.WIN
        score < dealerScore -> Result.LOSE
        else -> Result.PUSH
    }

    companion object {
        private const val TWENTY_ONE = 21

        fun from(name: Name, cards: Cards = Cards.empty()): Player = Player(name, cards)
    }
}
