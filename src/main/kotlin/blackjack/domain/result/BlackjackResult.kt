package blackjack.domain.result

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.State

class BlackjackResult(
    dealer: Dealer,
    players: List<Player>
) {
    val dealerCompeteResult: DealerResult
    val playerCompeteResults: List<PlayerResult>

    init {
        var dealerResult = DealerResult()
        val mutablePlayerResults = mutableListOf<PlayerResult>()

        players.forEach { player ->
            val dealerResultItem = compete(dealer = dealer, player = player)

            dealerResult = dealerResult.set(dealerResultItem)

            mutablePlayerResults.add(
                PlayerResult(
                    playerName = player.name,
                    competeResult = dealerResultItem.opposite()
                )
            )
        }

        dealerCompeteResult = dealerResult
        playerCompeteResults = mutablePlayerResults.toList()
    }

    private fun compete(dealer: Dealer, player: Player): CompeteResult {
        val playerScore = player.getScore()
        val playerState = player.state

        if (dealer.state == State.BUST ||
            (dealer.getScore() < playerScore && player.state != State.BUST) ||
            (dealer.state != State.BLACKJACK && player.state == State.BLACKJACK)
        ) {
            return CompeteResult.LOSE
        }

        if (playerState == State.BUST ||
            dealer.getScore() > playerScore ||
            (dealer.state == State.BLACKJACK && player.state != State.BLACKJACK)
        ) {
            return CompeteResult.WIN
        }

        return CompeteResult.DRAW
    }
}
