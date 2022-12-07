package model

private const val FIRST_SPREAD_CARD = 2

class PokerGame(
    val dealer: Dealer = Dealer(),
    val players: Players
) {
    init {
        repeat(FIRST_SPREAD_CARD) { plusCard() }
    }

    private fun plusCard() {
        players.hit(dealer)
        dealer.hit(dealer.pick())
    }

    fun getPlayers(): List<Player> = players.players
}
