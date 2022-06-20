package blackjack.model

class BlackjackGame(players: Players) {
    private var blackjackState: BlackjackState

    init {
        blackjackState = BlackjackState(players).giveInitCards()
    }

    fun isGameOver(): Boolean {
        return blackjackState.isAllPlayersGameOver()
    }

    fun playTurn(getContinue: (Player) -> Boolean): Player {
        val player = blackjackState.findNotOverPlayer()
        if (getContinue(player)) {
            blackjackState = blackjackState.giveCard(player)
        } else {
            blackjackState = blackjackState.setGameOver(player)
        }

        return blackjackState.findPlayer(player.name)
    }

    fun <T>withPlayers(f: (Players) -> T): T {
        return f(blackjackState.players)
    }
}
