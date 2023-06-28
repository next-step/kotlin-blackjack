package blackjack.domain

import java.lang.IllegalArgumentException

class BlackJackGameBoard(
    players: Set<String>,
    private val cardDeck: CardDeck,
    private val cardGameScore: CardGameScore = BlackJackScore()
) {
    private val players = players.map { Player.of(it) }

    init {
        playersDrawInitialCards()
    }

    fun getPlayers(): List<PlayerName> = players.map { it.playerName }

    fun getPlayerCards(playerName: PlayerName): Cards {
        return players.firstOrNull { it.playerName == playerName }?.cards ?: throw IllegalArgumentException("No such player($playerName)")
    }

    fun pickCard(playerName: PlayerName): Cards {
        return players.firstOrNull { it.playerName == playerName }?.receiveCard(cardDeck.pickCard()) ?: throw IllegalArgumentException("No such player($playerName)")
    }

    fun getPlayerScore(playerName: PlayerName): Score {
        return getPlayerCards(playerName).let { cardGameScore.calculateScore(it) }
    }

    private fun playersDrawInitialCards() {
        getPlayers().forEach { playerName ->
            repeat(INITIAL_CARD_COUNT) { pickCard(playerName) }
        }
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
