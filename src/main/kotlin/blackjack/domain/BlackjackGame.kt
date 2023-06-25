package blackjack.domain

import blackjack.domain.card.CardDeck

class BlackjackGame(
    turn: Turn = Turn(),
    val players: List<Player>,
    val cardDeck: CardDeck = CardDeck.randomCardDeck(),
) {
    var turn: Turn = turn
        private set

    init {
        require(players.isNotEmpty()) { "게임은 최소 1명 이상이 있어야 한다." }
    }

    fun firstDraw(): List<Hands> {
        check(turn.isDealingTurn()) { "first draw 턴이 아닙니다." }
        players.forEach { it.draw(cardDeck.draw()) }
        players.forEach { it.draw(cardDeck.draw()) }
        nextTurnChange()
        return players.map { it.hands() }
    }

    fun currentPlayerDraw(): Hands {
        val player = currentPlayer()
        player.draw(cardDeck.draw())
        if (player.isBust()) {
            nextTurnChange()
        }
        return Hands.from(player)
    }

    fun isEndGame(): Boolean = turn.isSameTurn(players.size)

    fun currentTurnPlayerName(): String = currentPlayer().name

    fun passToNextTurn() {
        currentPlayer().stay()
        nextTurnChange()
    }

    fun gameResult(): List<GameResult> = players.map { GameResult.from(it) }

    private fun currentPlayer(): Player {
        checkTurn()
        return players[turn.value]
    }

    private fun checkTurn() {
        check(turn.isDealingTurn().not()) { "첫 드로우가 시작되지 않았다." }
        check(turn.isHigherTurn(players.size)) { "모든 드로우가 종료되었다." }
    }

    private fun nextTurnChange() {
        turn = turn.nextTurn()
    }

    companion object {
        fun from(playerNames: List<String>) = BlackjackGame(players = playerNames.map { Player(it) })
    }
}
