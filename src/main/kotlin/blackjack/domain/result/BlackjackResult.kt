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

    private fun getPlayerEarningRate(dealer: Dealer, player: Player): Double {
        if (isDraw(dealer, player)) {
            return 1.0
        }

        if (isPlayerBlackjack(dealer, player)) {
            return 1.5
        }

        if (isDealerWin(dealer, player)) {
            return -1.0
        }

        return 0.0
    }

    private fun isDraw(dealer: Dealer, player: Player) = dealer.state == State.BUST ||
        (dealer.getScore() < player.getScore() && player.state != State.BUST)

    private fun isPlayerBlackjack(dealer: Dealer, player: Player) =
        dealer.state != State.BLACKJACK && player.state == State.BLACKJACK

    private fun isDealerWin(dealer: Dealer, player: Player) =
        player.state == State.BUST ||
            dealer.getScore() > player.getScore() ||
            (dealer.state == State.BLACKJACK && player.state != State.BLACKJACK)
}
