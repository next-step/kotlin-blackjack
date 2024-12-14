package blackjack.domain

import blackjack.entity.Dealer
import blackjack.entity.Player
import blackjack.entity.PlayerHand.Companion.BUST_LIMIT_VALUE

class BlackJackResult(
    private val dealer: Dealer,
    private val players: Set<Player>,
) {
    private var dealerResult: DealerResult? = null
    private var playerResults: List<PlayerResult>? = null

    fun getDealerResult(): DealerResult = dealerResult!!

    fun getPlayerResults(): List<PlayerResult> = playerResults!!

    fun calculate(): BlackJackResult {
        val dealerScore = dealer.hand.getTotalCardValue()

        playerResults =
            if (dealerScore > BUST_LIMIT_VALUE) {
                calculateDealerBust()
            } else {
                compareWithDealer(dealerScore)
            }

        dealerResult = DealerResult.from(dealer, playerResults!!)
        return this
    }

    private fun calculateDealerBust(): List<PlayerResult> {
        return players.map { player ->
            PlayerResult(player.name, winCount = 1, loseCount = 0, drawCount = 0)
        }
    }

    private fun compareWithDealer(dealerScore: Int): List<PlayerResult> {
        return players.map { player ->
            PlayerResult.from(player, dealerScore, BUST_LIMIT_VALUE)
        }
    }
}
