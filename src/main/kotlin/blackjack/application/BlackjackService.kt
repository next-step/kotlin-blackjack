package blackjack.application

import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.Players

class BlackjackService {
    fun initializeGame(
        names: List<String>,
        deck: Deck,
    ): Game {
        val players = Players.from(names)
        return Game(players, deck).apply { initialDeal() }
    }
}
