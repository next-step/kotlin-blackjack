package blackjack

class BlackJackGame(val players: List<Player>) {
    fun prepareDraw() {
        repeat(2) {
            players.forEach { it.draw(Card("A", Symbol.CLUBS)) }
        }
    }

    fun draw(isDraw: (name: String) -> Boolean, result: (Player) -> Unit = { }) {
        players.forEach {
            it.draw({ Card("A", Symbol.CLUBS) }, isDraw)
            result(it)
        }
    }
}
