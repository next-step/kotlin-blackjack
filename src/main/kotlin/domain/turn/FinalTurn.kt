package domain.turn

import domain.Result
import domain.card.CardDeck
import domain.dealer.Dealer
import domain.player.Players

object FinalTurn : Turn {

    override fun proceed(
        dealer: Dealer,
        players: Players,
        cardDeck: CardDeck,
        changeState: (Turn) -> Unit
    ) = Unit

    fun result(dealer: Dealer, allPlayers: Players): Result {
        if (dealer.isBust) {
            return Result(
                winners = allPlayers,
                losers = Players(emptyList())
            )
        }

        return Result(
            winners = winners(dealer, allPlayers),
            losers = losers(dealer, allPlayers),
        )
    }

    private fun maxPoint(dealer: Dealer, players: Players): Int {
        return players.list.filter {
            !it.isBust
        }.map {
            it.result
        }.filter {
            it > dealer.result
        }.maxOrNull() ?: Int.MAX_VALUE
    }

    private fun winners(dealer: Dealer, players: Players): Players {
        val maxPoint = maxPoint(dealer, players)
        return Players(
            players.list
                .filter {
                    it.result == maxPoint
                }
        )
    }

    private fun losers(dealer: Dealer, players: Players): Players {
        val maxPoint = maxPoint(dealer, players)
        return Players(
            players.list
                .filterNot {
                    it.result == maxPoint
                }
        )
    }
}
