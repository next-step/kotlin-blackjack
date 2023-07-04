package blackjack.domain.users

import blackjack.domain.BlackjackGame
import blackjack.domain.Cards
import blackjack.domain.result.DealerResult
import blackjack.model.DealerResultModel

data class Users(val players: List<Player>, val dealer: Dealer) {
    val userCards: Map<User, Cards>
        get() {
            val dealerCardMap = mapOf<User, Cards>(dealer to dealer.cards())
            return dealerCardMap.plus(players.associateBy<Player, User, Cards>({ it }, { it.cards() }))
        }

    fun cardReceivePossibleUsers(): List<Player> {
        return players.filter { player -> !player.isDeckComplete() }
    }

    fun dealerCardValue(): Int {
        return dealer.cardValues()
    }

    fun playerCards(): Map<Player, Cards> {
        return players.associateBy({ it }, { it.cards() })
    }

    fun dealerWinCount(): Int {
        return dealer.dealerResult.dealerResultModel.winCount
    }

    fun dealerLoseCount(): Int {
        return dealer.dealerResult.dealerResultModel.loseCount
    }

    fun dealerLose(
        blackjackGame: BlackjackGame
    ) {
        players.forEach(Player::win)
        val dealerResultModel = DealerResultModel(loseCount = players.size)
        blackjackGame.updateDealerResult(DealerResult(dealerResultModel))
    }

    fun calculateDealerResult(dealerCardValue: Int): DealerResultModel {
        var dealerWinCount = 0
        var dealerLoseCount = 0

        players.forEach {
            if (it.cardValues() in (dealerCardValue + 1)..21) {
                it.win()
                dealerLoseCount++
                return@forEach
            }
            dealerWinCount++
        }
        return DealerResultModel(dealerWinCount, dealerLoseCount)
    }
}
