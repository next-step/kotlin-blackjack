package blackjack.domain

object WinningDiscriminator {
    fun discrimination(dealer: Player, players: Players): List<BetResult> {
        var dealerEarnMoney = Money(0)

        val playerResults = players.map { player ->
            val playerEarnMoney = getBetResult(player.betMoney, dealer.currentHands(), player.currentHands())
            dealerEarnMoney -= playerEarnMoney
            BetResult(player, playerEarnMoney)
        }

        val dealerResult = BetResult(dealer, dealerEarnMoney)
        return listOf(dealerResult) + playerResults
    }

    fun getBetResult(money: Money, dealerCards: PlayerCards, playerCards: PlayerCards): Money {
        if (dealerCards.isBust()) return money
        if (playerCards.isBust()) return -money
        if (playerCards.isBlackJack() && dealerCards.isBlackJack()) return money
        if (playerCards.isBlackJack()) return money * 1.5
        if (dealerCards.score > playerCards.score) return -money
        if (dealerCards.score < playerCards.score) return money
        return Money(0)
    }
}
