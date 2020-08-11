package blackjack.domain

private const val SPLIT_CHARACTER = ","
private val PLAYER_REGULAR_EXPRESSION = "^[a-z|A-Z][a-z|A-Z,]*[a-z|A-Z]$".toRegex()
private val HIT_OR_STAY_REGULAR_EXPRESSION = "[y|n]".toRegex()
private const val HIT = "y"

class BlackjackGame(playerNames: String, private val cardDeck: CardDeck) {
    var players: Players
        private set
    var isEnd: Boolean = false
        private set

    init {
        players = parsingPlayers(playerNames)
        startGame()
    }

    private fun parsingPlayers(playerNames: String): Players {
        require(PLAYER_REGULAR_EXPRESSION.matches(playerNames)) { "플레이어의 이름은 영어이고, 구분자는 ','만 입력이 가능합니다." }
        return Players(playerNames.split(SPLIT_CHARACTER).map { Player(it) })
    }

    private fun startGame() {
        players.allPlayersReceivedCards(cardDeck)
    }

    fun hitOrStay(isHit: String): Player {
        require(HIT_OR_STAY_REGULAR_EXPRESSION.matches(isHit)) { "y, n 만 입력해주세요." }
        players.currentPlayerPickCard(isHit == HIT, cardDeck)
        return players.currentPlayer
    }

    fun nextTurn() {
        isEnd = players.getNextPlayer() === null
    }
}
