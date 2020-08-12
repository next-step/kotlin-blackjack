package blackjack.domain

private const val SPLIT_CHARACTER = ","
private val PLAYER_REGULAR_EXPRESSION = "^[a-z|A-Z|가-힣][a-z|A-Z|가-힣,]*[a-z|A-Z|가-힣]$".toRegex()
const val HIT = "y"
const val STAY = "n"
private val HIT_OR_STAY_REGULAR_EXPRESSION = "[{$HIT|$STAY}]".toRegex()

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
        require(PLAYER_REGULAR_EXPRESSION.matches(playerNames)) { "플레이어의 이름은 영문 또는 한글이며, 구분자는 ','만 입력이 가능합니다." }
        val players = playerNames.split(SPLIT_CHARACTER).map { Player(it) }.toMutableList()
        players.add(0, Dealer(playerCount = players.size))
        return Players(players)
    }

    private fun startGame() {
        players.allPlayersReceivedCards(cardDeck)
    }

    fun hitOrStay(isHit: String): Player {
        require(HIT_OR_STAY_REGULAR_EXPRESSION.matches(isHit)) { "$HIT, $STAY 만 입력해주세요." }
        players.currentPlayerPickCard(isHit == HIT, cardDeck)
        return players.currentPlayer
    }

    fun nextTurn() {
        isEnd = players.getNextPlayer() === null
    }
}
