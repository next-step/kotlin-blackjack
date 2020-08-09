package blackjack.domain

private const val SPLIT_CHARACTER = ","
private const val FIRST_PICK_COUNT = 2
private val PLAYER_REGULAR_EXPRESSION = "^[a-z|A-Z][a-z|A-Z,]*[a-z|A-Z]$".toRegex()
private val HIT_OR_STAY_REGULAR_EXPRESSION = "[y|n]".toRegex()
private const val HIT = "y"

class BlackjackGame(playerNames: String, private val cardDeck: CardDeck) {
    var players: List<Player> = emptyList()
    var currentPlayer: Player
        private set
    var isEnd: Boolean = false
        private set

    init {
        players = parsingPlayers(playerNames)
        currentPlayer = players[0]
        startGame()
    }

    private fun parsingPlayers(playerNames: String): List<Player> {
        require(PLAYER_REGULAR_EXPRESSION.matches(playerNames)) { "플레이어의 이름은 영어이고, 구분자는 ','만 입력이 가능합니다." }
        return playerNames.split(SPLIT_CHARACTER).map { Player(it) }
    }

    private fun startGame() {
        repeat(FIRST_PICK_COUNT) { players.forEach { it.addCard(cardDeck.pickCard()) } }
    }

    fun hitOrStay(isHit: String): Player {
        require(HIT_OR_STAY_REGULAR_EXPRESSION.matches(isHit)) { "y, n 만 입력해주세요." }
        currentPlayer.isHit = (isHit == HIT)
        if (currentPlayer.isHit) {
            currentPlayer.addCard(cardDeck.pickCard())
        }
        return currentPlayer
    }

    fun nextTurn() {
        val player = players.drop(players.indexOf(currentPlayer) + 1).plus(players).find { it.isHit }
        if (player == null) finishedGame() else currentPlayer = player
    }

    private fun finishedGame() {
        isEnd = true
    }
}
