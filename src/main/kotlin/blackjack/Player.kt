package blackjack

class Player(name: String, private val price: Int) : User(name) {

    init {
        require(price > 0) { "베팅 금액은 반드시 0보다 커야합니다." }
    }

    override fun getFirstDeal(): List<Card> {
        return listOf(cardDeck.cards[0], cardDeck.cards[1])
    }

    override fun getEvaluate(users: Users): Int {
        val dealer = users.getDealer()

        return when {
            isBlackJack() && dealer.isBlackJack() -> 0
            isBlackJack() -> (price * BLACK_JACK_REWARD_RATE).toInt()
            isWin(dealer) -> price
            else -> price.unaryMinus()
        }
    }

    private fun isWin(dealer: Dealer): Boolean {
        return dealer.isBust() || (!isBust() && cardDeck.getScore() > dealer.cardDeck.getScore())
    }

    companion object {
        private const val BLACK_JACK_REWARD_RATE = 1.5
    }
}

class Players(val players: List<Player>)
