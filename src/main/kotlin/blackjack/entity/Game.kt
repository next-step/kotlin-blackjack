package blackjack.entity

import blackjack.domain.BlackJackResult

class Game(val dealer: Dealer, val players: Set<Player>) {
    fun calculateResult(): BlackJackResult {
        return BlackJackResult(dealer, players).calculate()
    }
}
