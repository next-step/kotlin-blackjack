package domain.turn

import domain.Result
import domain.card.CardDeck
import domain.dealer.Dealer
import domain.player.Players

class FinalTurn(dealer: Dealer, players: Players, cardDeck: CardDeck) : Turn(dealer, players, cardDeck) {
    fun result(allPlayers: Players): Result {
        if (dealer.isBust) {
            return Result(
                winners = allPlayers,
                losers = Players(emptyList())
            )
        }

        return Result(
            winners = winners(allPlayers),
            losers = losers(allPlayers),
        )
    }

    private fun maxPoint(players: Players): Int {
        return players.list.filter {
            !it.isBust
        }.map {
            it.result
        }.filter {
            it > dealer.result
        }.maxOrNull() ?: Int.MAX_VALUE
    }

    private fun winners(players: Players): Players {
        val maxPoint = maxPoint(players)
        return Players(
            players.list
                .filter {
                    it.result == maxPoint
                }
        )
    }

    private fun losers(players: Players): Players {
        val maxPoint = maxPoint(players)
        return Players(
            players.list
                .filterNot {
                    it.result == maxPoint
                }
        )
    }
}
