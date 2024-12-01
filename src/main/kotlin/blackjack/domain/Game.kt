package blackjack.domain

data class Game(val players: List<Player>, val drawer: Deck) {
    fun startGame(
        onPrintResultCallback: ((List<Player>) -> Unit),
        onTurnCompleted: (Player) -> String,
    ) {
        drawer.generate()
        initTurn(onPrintResultCallback)
        players.forEach { player ->
            startTurn(player, onTurnCompleted, onPrintResultCallback)
        }
    }

    private fun initTurn(onPrintResultCallback: ((List<Player>) -> Unit)) {
        players.forEach { player ->
            repeat(2) { player.drawCard(CardDeck.drawCard()) }
        }
        onPrintResultCallback(players)
    }

    private fun startTurn(
        currentPlayer: Player,
        onTurnStarted: ((Player) -> String),
        onPrintResultCallback: (List<Player>) -> Unit,
    ) {
        while (!currentPlayer.isDone() && onTurnStarted(currentPlayer) == YES) {
            val card = CardDeck.drawCard()
            currentPlayer.drawCard(card)
            onPrintResultCallback(listOf(currentPlayer))
        }
    }

    companion object {
        private const val YES = "y"

        fun createGame(
            players: List<Player>,
            cardDeck: Deck = CardDeck,
        ): Game {
            return Game(players, cardDeck)
        }
    }
}
