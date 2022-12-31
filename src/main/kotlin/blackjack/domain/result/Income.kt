package blackjack.domain.result

import blackjack.domain.participantion.Dealer
import blackjack.domain.participantion.Player
import blackjack.domain.participantion.Price

class Income(private val winners: Winners, private val losers: Losers) {

    fun getAmount(player: Player): Int {
        val isWinner = winners.exist(player.name)
        val isLoser = losers.exist(player.name)

        return when {
            isWinner -> player.price.amount.unaryPlus()
            isLoser -> player.price.amount.unaryMinus()
            else -> player.price.amount.unaryPlus()
        }
    }

    fun getAmount(dealer: Dealer): Int {
        if (dealer.isBust()) {
            return dealer.price.amount
        }

        val winnersTotalPrice = winners.getTotalPrice()
        dealer.price.dec(winnersTotalPrice)

        val losersTotalPrice = losers.getTotalPrice()
        dealer.price.inc(losersTotalPrice)

        val blackJackPlayers = winners.getBlackJackPlayers()
        if (blackJackPlayers.isNotEmpty()) {
            blackJackIncome(blackJackPlayers, dealer)
        }

        return dealer.price.amount
    }

    private fun blackJackIncome(blackJackPlayers: List<Player>, dealer: Dealer) {
        if (dealer.cards.isBlackJack()) {
            return
        }

        blackJackPlayers.forEach { player ->
            val price = player.price
            val timesAmount = price.amount.times(BLACK_JACK_TIMES)
            val blackJackIncome = Price(timesAmount.toInt())

            dealer.price.dec(blackJackIncome)
        }
    }

    companion object {
        private const val BLACK_JACK_TIMES = 0.5
    }
}
