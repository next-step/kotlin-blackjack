package domain.turn

import domain.Result
import domain.card.CardDeck
import domain.gamer.Gamers
import domain.gamer.dealer.Dealer
import domain.gamer.player.Players

object FinalTurn : Turn {

    override fun proceed(
        gamers: Gamers,
        cardDeck: CardDeck,
        changeState: (Turn) -> Unit
    ) = Unit

    fun result(gamers: Gamers): Result {
        val dealer = gamers.dealer
        val players = gamers.players

        if (dealer.isBust) {
            return Result(
                winners = players,
                losers = Players(emptyList())
            )
        }

        return Result(
            winners = winners(dealer, players),
            losers = losers(dealer, players),
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
