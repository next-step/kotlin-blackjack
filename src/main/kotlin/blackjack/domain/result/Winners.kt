package blackjack.domain.result

import blackjack.domain.participantion.Dealer
import blackjack.domain.participantion.Player
import blackjack.domain.participantion.Price

class Winners(val players: List<Player>) : Rank(players) {

    companion object {
        private const val BLACK_JACK_TIMES = 0.5

        fun from(dealer: Dealer, players: List<Player>): Winners {
            val winners = players.filter { player -> player.isWin(dealer) }

            return Winners(winners)
        }
    }

    fun blackJackIncome(dealer: Dealer): Price {
        val blackJackPlayers = getBlackJackPlayers()

        if (dealer.isBlackJack()) {
            return Price.ZERO
        }

        val incomeAmount = blackJackPlayers.sumOf { player ->
            val price = player.price
            val timesAmount = price.amount.times(BLACK_JACK_TIMES)
            val blackJackIncome = Price(timesAmount.toInt())

            blackJackIncome.amount
        }

        return Price(incomeAmount)
    }
}
