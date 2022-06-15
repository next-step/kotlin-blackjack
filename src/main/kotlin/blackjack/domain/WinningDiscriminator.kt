package blackjack.domain

object WinningDiscriminator {
    fun discrimination(dealer: Player, players: Players): List<BetResult> {
        var dealerEarnMoney = Money(0)

        val playerResults = players.map { player ->
            val playerEarnMoney = getBetResult(player.betMoney, dealer, player)
            dealerEarnMoney -= playerEarnMoney
            BetResult(player, playerEarnMoney)
        }

        val dealerResult = BetResult(dealer, dealerEarnMoney)
        return listOf(dealerResult) + playerResults
    }

    fun getBetResult(money: Money, dealer: Player, player: Player): Money {
        if (dealer.isBust()) return money
        if (player.isBust()) return -money
        if (dealer.isBlackJack() && player.isBlackJack()) return money
        if (player.isBlackJack()) return money * 1.5
        if (dealer.score > player.score) return -money
        if (dealer.score < player.score) return money
        return Money(0)
    }
}
