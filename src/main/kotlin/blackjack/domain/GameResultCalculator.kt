package blackjack.domain

import blackjack.model.PlayerGameResults

class GameResultCalculator: ResultCalculator {

    override fun calculate(dealer: Dealer, players: Players): PlayerGameResults {
        TODO("Not yet implemented")
    }
}

interface ResultCalculator {
    fun calculate(dealer: Dealer, players: Players): PlayerGameResults
}
