package blackjack.model

import blackjack.model.gamer.Dealer
import blackjack.model.gamer.Gamer
import blackjack.model.gamer.Gamers

object Judge {
    fun calculateRevenue(gamer: Gamer, opponents: Gamers): BetMoney {
        return opponents.fold(BetMoney.ZERO) { betSum, opponent -> betSum + calculateRevenue(gamer, opponent) }
    }

    fun calculateRevenue(gamer: Gamer, opponent: Gamer): BetMoney {
        return if (gamer.getScore() >= opponent.getScore()) {
            withBetMoneyIfDealer(gamer, opponent).calculateRevenue()
        } else {
            - withBetMoneyIfDealer(opponent, gamer).calculateRevenue()
        }
    }

    private fun withBetMoneyIfDealer(gamer: Gamer, opponent: Gamer): Gamer {
        if (gamer is Dealer) {
            return gamer.copy(betMoney = opponent.betMoney)
        }
        return gamer
    }
}
