package blackjack.domain

const val SPLIT_CHARACTER = ","
const val HIT = "y"
const val STAY = "n"
val HIT_OR_STAY_REGULAR_EXPRESSION = "[{$HIT|$STAY}]".toRegex()
val PLAYER_REGULAR_EXPRESSION = "^[a-z|A-Z가-힣][a-z|A-Z가-힣,]*[a-z|A-Z가-힣]$".toRegex()

class BlackjackGame(val players: Players, private val cardDeck: CardDeck) {
    var isEnd: Boolean = false
        private set

    init {
        startGame()
    }

    private fun startGame() {
        players.allPlayersReceivedCards(cardDeck)
    }

    fun hitOrStay(isHit: String): Player? {
        if (!HIT_OR_STAY_REGULAR_EXPRESSION.matches(isHit)) return null
        players.currentPlayerPickCard(isHit == HIT, cardDeck)
        return players.currentPlayer
    }

    fun nextTurn() {
        isEnd = players.getNextPlayer() === null
    }
}
