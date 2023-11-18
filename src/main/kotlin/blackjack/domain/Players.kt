package blackjack.domain

data class Players(
    val dealer: Dealer,
    val players: List<Player>
) {
    fun initCard(deck: Deck) {
        val dealerInitCards = deck.init()
        dealer.init(dealerInitCards)

        players.forEach {
            val playerInitCards = deck.init()
            it.init(playerInitCards)
        }
    }

    fun getNames(): List<String> {
        return players.map {
            it.name
        }
    }

    companion object {
        fun init(dealer: Dealer, names: List<String>): Players {
            return Players(dealer = dealer, players = names.map { Player(it) })
        }
    }
}
