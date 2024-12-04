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

        val playersResult: List<PlayerGameResult> = players.map { player ->
            PlayerGameResult(
                player = player,
                isWin = player.compareWonOrNot(dealer),
            )
        }

        return GameResult(
            dealerName = dealer.name,
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

    companion object {
        private const val START_DRAW_COUNT = 2
    }

}
