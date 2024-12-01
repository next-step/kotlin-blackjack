package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player

class BlackjackGame(
    private val deck: Deck,
    dealer: Dealer,
    players: List<Player>,
) {
    val participants: List<Participant> = listOf(dealer).plus(players)

    fun start() {
        participants.forEach { participant ->
            repeat(START_DRAW_COUNT) { draw(participant) }
        }
    }

    fun draw(participant: Participant) {
        participant.receivedCard(deck.draw())
    }

    fun getGameResult(): GameResult {
        val dealer = participants.filterIsInstance<Dealer>().first()
        val players = participants.filterIsInstance<Player>()
        val dealerResultBuilder = DealerGameResultBuilder(dealer)

        val playersResult: List<PlayerGameResult> = players.map { player ->
            val playerWin = player.compareWonOrNot(dealer)
            dealerResultBuilder.record(!playerWin)
            PlayerGameResult(
                player = player,
                isWin = playerWin,
            )
        }

        return GameResult(
            dealerResult = dealerResultBuilder.build(),
            playersResult = playersResult,
        )
    }

    private fun Player.compareWonOrNot(dealer: Dealer): Boolean {
        if (dealer.cards.isBusted()) {
            return true
        }

        if (this.cards.isBusted()) {
            return false
        }

        return this.cards.sum() > dealer.cards.sum()
    }

    private fun DealerGameResultBuilder.record(isWin: Boolean) {
        if (isWin) {
            this.win()
        } else {
            this.lose()
        }
    }

    companion object {
        private const val START_DRAW_COUNT = 2
    }

}
