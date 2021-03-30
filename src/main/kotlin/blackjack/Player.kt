package blackjack

class Player(name: String, val price: Int) : User(name) {

    init {
        require(price > 0) { "베팅 금액은 반드시 0보다 커야합니다." }
    }

    override fun getFirstDeal(): List<Card> {
        return listOf(cardDeck.cards[0], cardDeck.cards[1])
    }
}

class Players(val players: List<Player>)
