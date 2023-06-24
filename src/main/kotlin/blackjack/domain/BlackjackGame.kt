package blackjack.domain

import blackjack.domain.card.CardDeck

class BlackjackGame(
    turn: Int = -1,
    val players: List<Player>,
    val cardDeck: CardDeck = CardDeck.randomCardDeck(),
) {
    var turn: Int = turn
        private set

    fun firstDraw(): List<Hands> {
        check(turn == BEFORE_FIRST_DRAW_TURN) { "first draw 턴이 아닙니다." }
        players.forEach { it.draw(cardDeck.draw()) }
        players.forEach { it.draw(cardDeck.draw()) }
        turn++
        return players.map { it.hands() }
    }

    fun currentPlayerDraw(): Hands {
        val player = currentPlayer()
        player.draw(cardDeck.draw())
        if (player.isBust()) {
            turn++
        }
        return Hands.from(player)
    }

    fun isEndGame(): Boolean = players.size == turn

    fun currentTurnPlayerName(): String = currentPlayer().name

    fun passToNextTurn() {
        currentPlayer().stay()
        turn++
    }

    fun gameResult(): List<GameResult> = players.map { GameResult.from(it) }

    private fun currentPlayer(): Player {
        checkTurn()
        return players[turn]
    }

    private fun checkTurn() {
        check(turn != BEFORE_FIRST_DRAW_TURN) { "첫 드로우가 시작되지 않았다." }
        check(turn < players.size) { "모든 드로우가 종료되었다." }
    }

    companion object {
        private const val BEFORE_FIRST_DRAW_TURN = -1

        fun from(playerNames: List<String>) = BlackjackGame(players = playerNames.map { Player(it) })
    }
}
