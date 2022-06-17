package blackjack.model

import blackjack.model.player.Player
import blackjack.model.player.Players

class Game(
    private val gameHost: GameHost,
    private val players: Players,
    private var turnPlayer: Player
) {

    fun start() {
        gameHost.shuffleCards()
        gameHost.provideCardTo(players, INITIAL_PROVIDED_CARD_COUNT)

        turnPlayer = players.first
    }

    fun provideCardToTurnPlayer() {
        gameHost.provideOneCardTo(turnPlayer)
    }

    fun changeTurn() {
        turnPlayer = players.findNext(turnPlayer)
    }

    companion object {
        private const val INITIAL_PROVIDED_CARD_COUNT = 2
    }
}
