package blackjack.domain

data class Players(
    val dealer: Dealer,
    val players: List<Player>
) {
    fun initCard() {
        val dealerInitCards = dealer.drawInitCards()
        dealer.init(dealerInitCards)

        players.forEach {
            val playerInitCards = dealer.drawInitCards()
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
