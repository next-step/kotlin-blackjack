package blackjack.domain.result

import blackjack.domain.participantion.Dealer
import blackjack.domain.participantion.Participant
import blackjack.domain.participantion.Player
import blackjack.domain.participantion.Price

class Income(private val participant: Participant, private val price: Price) {
    operator fun component1() = participant
    operator fun component2() = price

    companion object {
        private const val BLACK_JACK_TIMES = 0.5

        fun from(winners: Winners, losers: Losers, dealer: Dealer): List<Income> {
            val dealerIncomePrice = Price(getAmount(winners, losers, dealer))
            val dealerIncome = Income(dealer, dealerIncomePrice)

            val winnersIncome = winners.players
                .map { player -> Income(player, player.price) }

            val losersIncome = losers.players
                .map { player ->
                    player.losePrice()
                    Income(player, player.price)
                }

            return listOf(dealerIncome) + winnersIncome + losersIncome
        }

        private fun getAmount(winners: Winners, losers: Losers, dealer: Dealer): Int {
            if (dealer.isBust()) {
                return dealer.priceAmount
            }

            val winnersTotalPrice = winners.getTotalPrice()
            dealer.price.decrease(winnersTotalPrice)

            val losersTotalPrice = losers.getTotalPrice()
            dealer.price.increase(losersTotalPrice)

            val blackJackPlayers = winners.getBlackJackPlayers()
            if (blackJackPlayers.isNotEmpty()) {
                blackJackIncome(blackJackPlayers, dealer)
            }

            return dealer.priceAmount
        }

        private fun blackJackIncome(blackJackPlayers: List<Player>, dealer: Dealer) {
            if (dealer.isBlackJack()) {
                return
            }

            blackJackPlayers.forEach { player ->
                val price = player.price
                val timesAmount = price.amount.times(BLACK_JACK_TIMES)
                val blackJackIncome = Price(timesAmount.toInt())

                dealer.price.decrease(blackJackIncome)
            }
        }
    }
}
