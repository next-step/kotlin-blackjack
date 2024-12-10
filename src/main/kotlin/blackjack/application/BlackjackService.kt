package blackjack.application

import blackjack.domain.Bet
import blackjack.domain.Game
import blackjack.domain.Players

class BlackjackService {
    fun createGame(command: CreateGameCommand): Game {
        val bets = command.bets.map { Bet(it) }
        val players = Players.from(command.names)

        players.placeBets(bets)

        val game = Game(players, command.deck)
        return game.apply { initialDeal() }
    }
}
