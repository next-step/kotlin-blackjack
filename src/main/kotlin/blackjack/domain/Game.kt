package blackjack.domain

object Game {
    private const val START_CARD_SIZE = 2
    private const val STOP_NUMBER = 21
    private val deck = Deck()
    val players: MutableList<Player> = mutableListOf()
    val isFinished: Boolean = checkPlayersScore()

    fun addPlayer(player: Player) {
        var newPlayer = player

        repeat(START_CARD_SIZE) {
            val getCardPlayer = newPlayer.getCards(deck.cards.pick())
            newPlayer = getCardPlayer
        }
        players.add(newPlayer)
    }

    private fun checkPlayersScore(): Boolean {
        players.forEach {
            if (isStop(it.cards.getScore())) return true
        }
        return false
    }

    private fun isStop(score: Int) = score >= STOP_NUMBER
}
