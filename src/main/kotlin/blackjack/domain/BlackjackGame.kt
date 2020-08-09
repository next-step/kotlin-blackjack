package blackjack.domain

import java.util.regex.Pattern

private const val SPLIT_CHARACTER = ","
private const val FIRST_PICK_COUNT = 2
private const val PLAYER_REGULAR_EXPRESSION = "^[a-z|A-Z][a-z|A-Z,]*[a-z|A-Z]$"
private const val HIT_OR_STAY_REGULAR_EXPRESSION = "[y|n]"
private const val HIT = "y"

class BlackjackGame(playerNames: String, private val cardDeck: CardDeck) {
    var players: List<Player> = emptyList()
    var currentPlayer: Player
        private set

    init {
        players = parsingPlayers(playerNames)
        currentPlayer = players[0]
        startGame()
    }

    private fun parsingPlayers(playerNames: String): List<Player> {
        require(Pattern.compile(PLAYER_REGULAR_EXPRESSION).matcher(playerNames).matches())
        return playerNames.split(SPLIT_CHARACTER).map { Player(it) }
    }

    fun getCardCount() = cardDeck.cards.size
    fun getCurrentPosition() = cardDeck.currentPosition

    private fun startGame() {
        repeat(FIRST_PICK_COUNT) { players.forEach { it.addCard(cardDeck.pickCard()) } }
    }

    fun hitOrStay(isHit: String) {
        require(Pattern.compile(HIT_OR_STAY_REGULAR_EXPRESSION).matcher(isHit).matches())
        currentPlayer.isHit = (isHit == HIT)
        if (currentPlayer.isHit) {
            currentPlayer.addCard(cardDeck.pickCard())
        }
    }

    private fun nextTurn() {
        val player = players.subList(players.indexOf(currentPlayer), players.lastIndex).plus(players).find { it.isHit }
        if (player == null) finishedGame() else currentPlayer = player
    }

    private fun finishedGame() {
    }
}
