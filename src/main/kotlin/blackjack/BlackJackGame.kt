package blackjack

class BlackJackGame(val players: List<CardPlayer>, private val deck: Blackjack) {
    val dealer: CardPlayer.Dealer = CardPlayer.Dealer()
    fun prepareDraw(result: (List<CardPlayer>) -> Unit = { }) {
        repeat(2) {
            players.forEach { it.accept(deck.next()) }
            dealer.accept(deck.next())
        }
        result(allPlayers())
    }

    fun draw(isDraw: (name: String) -> Boolean, result: (CardPlayer) -> Unit = { }) {
        players.forEach {
            it.draw(Draw(deck::next, isDraw, result))
        }
    }

    fun allPlayers(): List<CardPlayer> = players + dealer

    fun endDraw(taken: () -> Unit = { }) {
        if (dealer.lastWant()) {
            dealer.take { deck.next() }
            taken()
        }
    }
}
