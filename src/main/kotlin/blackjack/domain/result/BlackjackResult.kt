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
        playerCompeteResults = players.map { player ->
            getPlayerEarningRate(player = player, dealer = dealer)
            PlayerResult(
                player = player,
                earningRate = getPlayerEarningRate(dealer = dealer, player = player)
            )
        }

        dealerCompeteResult = DealerResult(
            -playerCompeteResults.sumOf {
                it.getEarnMoney()
            }
        )
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

    private fun getPlayerEarningRate(dealer: Dealer, player: Player): Double {
        val playerScore = player.getScore()
        val playerState = player.state

        if (dealer.state == State.BUST ||
            (dealer.getScore() < playerScore && player.state != State.BUST)
        ) {
            return 1.0
        }

        if (dealer.state != State.BLACKJACK && player.state == State.BLACKJACK) {
            return 1.5
        }

        if (playerState == State.BUST ||
            dealer.getScore() > playerScore ||
            (dealer.state == State.BLACKJACK && player.state != State.BLACKJACK)
        ) {
            return -1.0
        }

        return 0.0
    }
}
