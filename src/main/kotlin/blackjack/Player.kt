package blackjack

class Player(name: String) : User(name) {
    override fun getFirstDeal(): List<Card> {
        return listOf(cardDeck.cards[0], cardDeck.cards[1])
    }
}

class Players(val players: List<Player>)
