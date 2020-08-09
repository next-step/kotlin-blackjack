package blackjack.domain

import java.util.regex.Pattern

private const val SPLIT_CHARACTER = ","
private const val FIRST_PICK_COUNT = 2
private const val REGULAR_EXPRESSION = "^[a-z|A-Z][a-z|A-Z,]*[a-z|A-Z]$"

class BlackjackGame(playerNames: String, private val cardDeck: CardDeck) {
    var players: List<Player> = emptyList()

    init {
        players = parsingPlayers(playerNames)
    }

    private fun parsingPlayers(playerNames: String): List<Player> {
        require(Pattern.compile(REGULAR_EXPRESSION).matcher(playerNames).matches())
        return playerNames.split(SPLIT_CHARACTER).map { Player(it) }
    }

    fun getCardCount() = cardDeck.cards.size
    fun getCurrentPosition() = cardDeck.currentPosition

    fun startGame() {
        for (player in players) {
            repeat(FIRST_PICK_COUNT) { player.getCard(cardDeck.pickCard()) }
        }
    }
}
