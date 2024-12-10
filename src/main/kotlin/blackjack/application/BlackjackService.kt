package blackjack.application

import blackjack.domain.Bet
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.Players

class BlackjackService {
    fun initializeGame(
        names: List<String>,
        deck: Deck,
    ): Game {
        val players = Players.from(names)
        val game = Game(players, deck)
        return game.apply { initialDeal() }
    }

    fun createGame(command: CreateGameCommand): Game {
        val bets = command.bets.map { Bet(it) }
        val players = Players.from(command.names)

        players.placeBets(bets)

        val game = Game(players, command.deck)
        return game.apply { initialDeal() }
    }
}
