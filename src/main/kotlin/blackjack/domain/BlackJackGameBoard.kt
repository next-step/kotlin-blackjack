package blackjack.domain

import java.lang.IllegalArgumentException

class BlackJackGameBoard(
    players: Set<String>,
    private val cardDeck: CardDeck,
    private val cardGameScore: CardGameScore = BlackJackScore()
) {

    private val players = players.map { Player.of(it) }

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
}
