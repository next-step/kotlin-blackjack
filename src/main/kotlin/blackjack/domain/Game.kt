package blackjack.domain

data class Game(val players: List<Player>, val drawer: CardDeckStrategy) {
    fun startGame(
        onPrintResultCallback: ((List<Player>) -> Unit),
        onTurnCompleted: (Player) -> String,
    ) {
        initTurn(onPrintResultCallback)
        players.forEach { player ->
            startTurn(player, onTurnCompleted, onPrintResultCallback)
        }
    }

    fun initTurn(onPrintResultCallback: ((List<Player>) -> Unit)) {
        players.forEach { player ->
            repeat(2) { player.drawCard(CardDeck.drawCard()) }
        }
        onPrintResultCallback(players)
    }

    fun startTurn(
        currentPlayer: Player,
        onTurnStarted: ((Player) -> String),
        onPrintResultCallback: (List<Player>) -> Unit,
    ) {
        if (currentPlayer.isDone()) return
        while (onTurnStarted(currentPlayer) == "y") {
            val card = CardDeck.drawCard()
            currentPlayer.drawCard(card)
            onPrintResultCallback(listOf(currentPlayer))
        }
    }

    fun getResult() = players

    companion object {
        fun createGame(players: List<Player>, cardDeck: CardDeckStrategy = CardDeck): Game {
            return Game(players, cardDeck)
        }
    }
}
