package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.domain.result.GameResult
import blackjack.domain.result.GameResultType
import blackjack.domain.result.PlayerGameResult

class BlackjackGame(
    private val deck: Deck,
    val dealer: Dealer,
    val players: List<Player>,
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

    fun getGameResult(): GameResult {
        val playersResult: List<PlayerGameResult> = players.map { player ->
            PlayerGameResult(
                player = player,
                resultType = player.resultType(dealer),
            )
        }

        return GameResult(
            dealerName = dealer.name,
            playersResult = playersResult,
        )
    }

    private fun Player.resultType(dealer: Dealer): GameResultType {
        if (this.cards.isTwoCardBlackjack()) {
            return if (dealer.cards.isTwoCardBlackjack()) {
                GameResultType.PUSH
            } else {
                GameResultType.BLACKJACK_WIN
            }
        }

        if (dealer.cards.isBusted()) {
            return GameResultType.WIN
        }

        if (this.cards.isBusted()) {
            return GameResultType.LOSE
        }

        return when {
            this.cards.sum() == dealer.cards.sum() -> GameResultType.PUSH
            this.cards.sum() > dealer.cards.sum() -> GameResultType.WIN
            else -> GameResultType.LOSE
        }
    }

    companion object {
        private const val START_DRAW_COUNT = 2
    }

}
