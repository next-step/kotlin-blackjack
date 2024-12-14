package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.domain.result.GameResult
import blackjack.domain.result.PlayerGameResult
import blackjack.domain.result.GameResultJudge

class BlackjackGame(
    private val deck: Deck,
    val dealer: Dealer,
    val players: List<Player>,
    private val gameResultJudge: GameResultJudge,
) {
    fun start() {
        drawInitialCards(dealer)
        players.forEach { player ->
            drawInitialCards(player)
        }
    }

    private fun drawInitialCards(participant: Participant) {
        repeat(START_DRAW_COUNT) {
            draw(participant)
        }
    }

    fun draw(participant: Participant) {
        participant.receivedCard(deck.draw())
    }

    fun judgeGame(): GameResult {
        val playersResult: List<PlayerGameResult> = players.map { player ->
            PlayerGameResult(
                player = player,
                resultType = gameResultJudge.judge(player.cards, dealer.cards),
            )
        }

        return GameResult(
            dealerName = dealer.name,
            playersResult = playersResult,
        )
    }

    companion object {
        private const val START_DRAW_COUNT = 2
    }

}
