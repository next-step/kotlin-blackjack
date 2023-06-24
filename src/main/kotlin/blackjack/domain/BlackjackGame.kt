package blackjack.domain

import blackjack.domain.card.CardDeck

class BlackjackGame(
    turn: Int = -1,
    val players: Players,
    val cardDeck: CardDeck = CardDeck.randomCardDeck(),
) {
    var turn: Int = turn
        private set

    fun firstDraw(): List<DrawResult> {
        check(turn == BEFORE_FIRST_DRAW_TURN) { "first draw 턴이 아닙니다." }
        players.drawAllPlayer { cardDeck.draw() }
        players.drawAllPlayer { cardDeck.draw() }
        turn++
        return players.allPlayersDrawResult()
    }

    fun isEndGame(): Boolean = players.isEndTurn(turn)

    fun currentTurnPlayerName(): String {
        check(turn != BEFORE_FIRST_DRAW_TURN) { "첫 드로우가 시작되지 않았다." }
        check(players.isEndTurn(turn).not()) { "모든 드로우가 종료되었다." }
        return players.playerName(turn)
    }

    companion object {
        private const val BEFORE_FIRST_DRAW_TURN = -1
    }
}
