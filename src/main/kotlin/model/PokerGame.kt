package model

private const val FIRST_SPREAD_CARD = 2

class PokerGame(
    val dealer: Dealer = Dealer(Deck()),
    val players: Players
) {
    init {
        repeat(FIRST_SPREAD_CARD) { plusCard() }
    }

    private fun plusCard() = players.hit(dealer)

    fun getPlayers(): List<Player> = players.players
}
