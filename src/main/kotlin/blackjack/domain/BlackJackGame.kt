package blackjack.domain

class BlackJackGame(private val players: Players, private val deck: Blackjack) {

    fun prepareDraw() {
        for (player in players.allPlayers() * PREPARE_DRAWS) {
            player.accept(deck.next())
        }
    }

    fun draw(isDraw: (name: String) -> Boolean, result: (CardPlayer) -> Unit = { }) {
        players.all {
            it.draw(Draw(deck::next, isDraw, result))
        }
    }

    fun endDraw(taken: () -> Unit = { }) {
        val lastTake = players.lastTake()

        if (lastTake.required()) {
            lastTake.take(deck.next())
            taken()
        }
    }

    companion object {
        const val PREPARE_DRAWS = 2
    }
}
