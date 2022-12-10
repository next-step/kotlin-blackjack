package blackjack.domain

object Game {
    private const val STOP_NUMBER = 21
    val players: MutableList<Player> = mutableListOf()

    fun hit(player: Player) {
        player.getCards(Deck.draw())
    }

    fun isFinished(): Boolean {
        players.forEach {
            if (isStop(it.cards.getScore())) return true
        }
        return false
    }

    private fun isStop(score: Int) = score >= STOP_NUMBER
}
