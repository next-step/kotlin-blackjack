package blackjack.domain

object Game {
    private const val START_CARD_SIZE = 2
    private const val STOP_NUMBER = 21
    private val deck = Deck()
    val players: MutableList<Player> = mutableListOf()

    fun addPlayer(player: Player) {
        repeat(START_CARD_SIZE) {
            player.getCards(deck.cards.pick())
        }
        players.add(player)
    }

    fun hit(player: Player) {
        player.getCards(deck.cards.pick())
    }

    fun isFinished(): Boolean {
        players.forEach {
            if (isStop(it.cards.getScore())) return true
        }
        return false
    }

    private fun isStop(score: Int) = score >= STOP_NUMBER
}
