package blackjack.model

class BlackjackGame(players: Players) {
    private var blackjackState: BlackjackState

    init {
        blackjackState = BlackjackState(players).initDeal()
    }

    fun isGameOver(): Boolean {
        return blackjackState.isAllPlayersOver()
    }

    fun playTurn(getHit: (Player) -> Boolean): Player {
        val player = blackjackState.findNotOverPlayer()
        if (getHit(player)) {
            blackjackState = blackjackState.hit(player)
        } else {
            blackjackState = blackjackState.stay(player)
        }

        return blackjackState.findPlayer(player.name)
    }

    fun <T> withPlayers(f: (Players) -> T): T {
        return f(blackjackState.players)
    }
}
