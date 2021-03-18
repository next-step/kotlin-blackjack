package blackjack

class BlackJackGame(val players: List<Player>, private val deck: Blackjack) {
    val dealer: Player.Dealer = Player.Dealer()
    fun prepareDraw(result: (List<Player>) -> Unit = { }) {
        repeat(2) {
            players.forEach { it.accept(deck.next()) }
            dealer.accept(deck.next())
        }
        result(allPlayers())
    }

    fun draw(isDraw: (name: String) -> Boolean, result: (Player) -> Unit = { }) {
        players.forEach {
            it.draw(Draw(deck::next, isDraw, result))
        }
    }

    fun allPlayers(): List<Player> = players + dealer

    fun endDraw(taken: () -> Unit = { }) {
        if (dealer.lastWant()) {
            dealer.take { deck.next() }
            taken()
        }
    }
}
