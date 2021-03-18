package blackjack

class BlackJackGame(val players: Players, private val deck: Blackjack) {

    fun prepareDraw(result: (Players) -> Unit = { }) {
        repeat(2) {
            players.all { it.accept(deck.next()) }
            players.dealer.accept(deck.next())
        }
        result(allPlayers())
    }

    fun draw(isDraw: (name: String) -> Boolean, result: (CardPlayer) -> Unit = { }) {
        players.all {
            it.draw(Draw(deck::next, isDraw, result))
        }
    }

    fun allPlayers(): Players = players.allPlayers()

    fun endDraw(taken: () -> Unit = { }) {
        if (players.dealer.lastWant()) {
            players.dealer.take { deck.next() }
            taken()
        }
    }
}
