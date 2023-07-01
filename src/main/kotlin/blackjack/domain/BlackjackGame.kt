package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.gamestate.Competition
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants

class BlackjackGame(
    turn: Turn = Turn(),
    val dealer: Participant = Dealer(),
    val players: Participants,
    val cardDeck: CardDeck = CardDeck.randomCardDeck(),
) {
    var turn: Turn = turn
        private set

    fun firstDraw(): Pair<DealerHands, List<PlayerHands>> {
        check(turn.isDealingTurn()) { "first draw 턴이 아닙니다." }
        repeat(FIRST_DRAW_COUNT) { drawDealerAndPlayers() }
        nextTurnChange()
        return dealerFirstDrawHand() to players.hands()
    }

    fun currentPlayerDraw(): PlayerHands {
        checkTurn()
        val participantDraw = players.participantDraw(turn.value) { cardDeck.draw() }
        if (participantDraw.second) {
            nextTurnChange()
        }
        return participantDraw.first
    }

    fun isPlayerTurnEnd(): Boolean = turn.isSameTurn(players.size())

    fun currentTurnPlayerName(): String {
        checkTurn()
        return players.participantName(turn.value)
    }

    fun passToNextTurn() {
        checkTurn()
        players.participantStay(turn.value)
        nextTurnChange()
    }

    fun dealerDraw() {
        check(isPlayerTurnEnd()) { "딜러턴이 종료되지 않아 딜러에게 드로우할 수 없다." }
        dealer.draw(cardDeck.draw())
    }

    fun isDealerTurnEnd(): Boolean {
        check(isPlayerTurnEnd()) { "유저턴이 종료되지 않아 확인할 수 없다." }
        return dealer.isFinished()
    }

    fun gameResult(): GameResult {
        check(isPlayerTurnEnd() && dealer.isFinished()) { "게임이 종료되지 않아 결과를 확인할 수 없다." }
        val playerGameResults = players.competeWith(dealer)
        val dealerGameResult = DealerGameResult.of(dealer, parseDealerCompetitions(playerGameResults))
        return GameResult(dealerGameResult, playerGameResults)
    }

    private fun drawDealerAndPlayers() {
        dealer.draw(cardDeck.draw())
        players.drawAllParticipants { cardDeck.draw() }
    }

    private fun nextTurnChange() {
        turn = turn.nextTurn()
    }

    private fun dealerFirstDrawHand() = DealerHands(cards = setOf(dealer.cards().first()))

    private fun checkTurn() {
        check(turn.isDealingTurn().not()) { "첫 드로우가 시작되지 않았다." }
        check(turn.isHigherTurn(players.size())) { "모든 드로우가 종료되었다." }
    }

    private fun parseDealerCompetitions(playerGameResults: List<PlayerGameResult>): Map<Competition, Int> =
        playerGameResults.map { it.competition.toOpposite() }
            .groupingBy { it }
            .eachCount()

    companion object {
        private const val FIRST_DRAW_COUNT = 2

        fun from(playerNames: List<String>) = BlackjackGame(players = Participants.playersFrom(playerNames))
    }
}
