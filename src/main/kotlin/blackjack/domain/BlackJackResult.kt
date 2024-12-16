package blackjack.domain

import blackjack.domain.PlayerResult.Companion.BET_WIN_MULTIPLIER
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
                compareWithDealer(dealerScore, dealer.isBlackJack())
            }

        dealerResult = DealerResult.from(dealer, playerResults!!)
        return this
    }

    private fun calculateDealerBust(): List<PlayerResult> {
        return players.map { player ->
            PlayerResult(player.name, dealerBustAndPlayerBlackJack(player))
        }
    }

    private fun dealerBustAndPlayerBlackJack(player: Player): Int {
        return if (player.isBlackJack()) {
            (player.betAmount * BET_WIN_MULTIPLIER).toInt()
        } else {
            player.betAmount
        }
    }

    private fun compareWithDealer(
        dealerScore: Int,
        isDealerBlackJack: Boolean,
    ): List<PlayerResult> {
        return players.map { player ->
            PlayerResult.from(player, isDealerBlackJack, dealerScore, BUST_LIMIT_VALUE)
        }
    }
}
