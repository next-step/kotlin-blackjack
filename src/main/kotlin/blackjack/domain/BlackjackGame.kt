package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Player

class BlackjackGame(
    turn: Turn = Turn(),
    val dealer: Participant = Dealer(),
    val players: List<Participant>,
    val cardDeck: CardDeck = CardDeck.randomCardDeck(),
) {
    var turn: Turn = turn
        private set

    init {
        require(players.isNotEmpty()) { "게임은 최소 1명 이상이 있어야 한다." }
    }

    fun firstDraw(): List<Hands> {
        check(turn.isDealingTurn()) { "first draw 턴이 아닙니다." }
        repeat(FIRST_DRAW_COUNT) { drawDealerAndPlayers() }
        nextTurnChange()
        return listOf(dealerFirstDrawHand()) + players.map { Hands.from(it) }
    }

    fun currentPlayerDraw(): Hands {
        val player = currentPlayer()
        player.draw(cardDeck.draw())
        if (player.isFinished()) {
            nextTurnChange()
        }
        return Hands.from(player)
    }

    fun isPlayerTurnEnd(): Boolean = turn.isSameTurn(players.size)

    fun currentTurnPlayerName(): String = currentPlayer().name()

    fun passToNextTurn() {
        currentPlayer().stay()
        nextTurnChange()
    }

    fun isDealerTurnEnd(): Boolean {
        check(isPlayerTurnEnd()) { "유저턴이 종료되지 않아 확인할 수 없다." }
        return dealer.isFinished()
    }

    fun dealerDraw() {
        check(isDealerTurnEnd().not()) { "딜러턴이 종료되지 않았다." }
        dealer.draw(cardDeck.draw())
    }

    fun gameResult(): List<GameResult> = players.map { GameResult.from(it) }

    private fun nextTurnChange() {
        turn = turn.nextTurn()
    }

    private fun drawDealerAndPlayers() {
        dealer.draw(cardDeck.draw())
        players.forEach { it.draw(cardDeck.draw()) }
    }

    private fun dealerFirstDrawHand() = Hands(playerName = dealer.name(), cards = setOf(dealer.cards().first()))

    private fun currentPlayer(): Participant {
        checkTurn()
        return players[turn.value]
    }

    private fun checkTurn() {
        check(turn.isDealingTurn().not()) { "첫 드로우가 시작되지 않았다." }
        check(turn.isHigherTurn(players.size)) { "모든 드로우가 종료되었다." }
    }

    companion object {
        private const val FIRST_DRAW_COUNT = 2

        fun from(playerNames: List<String>) = BlackjackGame(players = playerNames.map { Player.from(it) })
    }
}
