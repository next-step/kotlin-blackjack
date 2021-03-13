package blackjack

class BlackJackGame(val players: List<Player>, private val deck: Blackjack) {
    fun prepareDraw(result: (List<Player>) -> Unit = { }) {
        repeat(2) {
            players.forEach { it.accept(deck.next()) }
        }
        result(players)
    }

    fun draw(isDraw: (name: String) -> Boolean, result: (Player) -> Unit = { }) {
        players.forEach {
            it.draw(Draw(deck::next, isDraw, result))
        }
    }
}
