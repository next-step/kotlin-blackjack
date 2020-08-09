package blackjack.domain

class BlackJackGame(
    playerNames: List<String>,
    private val deck: DrawStrategy
) {
    private val players: Map<String, Player> = playerNames.associateWith { Player(it) }
    val playerNames
        get() = players.map { it.key }

    init {
        players.values.forEach { it.deal(deck) }
    }

    fun hit(playerName: String) {
        getPlayer(playerName).hit(deck)
    }

    fun playerCardsOf(playerName: String): List<Card> {
        return getPlayer(playerName).getCards()
    }

    fun playerScoreOf(playerName: String): Int {
        return getPlayer(playerName).getScore()
    }

    private fun getPlayer(playerName: String) = players[playerName]
        ?: throw IllegalArgumentException("($playerName)해당 사용자를 찾을 수 없습니다.")
}
