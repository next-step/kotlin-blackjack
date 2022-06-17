package blackjack.model

import blackjack.model.player.Player
import blackjack.model.player.Players

class Game(
    private val gameHost: GameHost = GameHost(),
    val players: Players,
    var turnPlayer: Player = players.first
) {

    val turnPlayerName
        get() = turnPlayer.name

    fun start() {
        gameHost.shuffleCards()
        gameHost.provideCardTo(players, INITIAL_PROVIDED_CARD_COUNT)
    }

    fun provideCardToTurnPlayer() = gameHost.provideOneCardTo(turnPlayer)

    fun changeTurn() {
        turnPlayer.needMoreCard = false
        turnPlayer = players.findNext(turnPlayer)
    }

    fun isNotEnd() = turnPlayer.needMoreCard

    companion object {
        private const val INITIAL_PROVIDED_CARD_COUNT = 2
    }
}
