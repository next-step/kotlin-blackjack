package blackjack

class Player(name: String, val price: Int) : User(name) {

    init {
        require(price > 0) { "베팅 금액은 반드시 0보다 커야합니다." }
    }

    override fun getFirstDeal(): List<Card> {
        return listOf(cardDeck.cards[0], cardDeck.cards[1])
    }

    override fun getEvaluate(users: Users): Int {
        if (isBlackJack())
            return (price * BLACK_JACK_REWARD_RATE).toInt()

        return 0
    }

    companion object {
        private const val BLACK_JACK_REWARD_RATE = 1.5
    }
}

class Players(val players: List<Player>)
