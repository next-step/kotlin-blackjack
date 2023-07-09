package domain.turn

import domain.Result
import domain.card.CardDeck
import domain.gamer.Gamers
import domain.gamer.player.Players

object FinalTurn : Turn {

    override fun proceed(
        gamers: Gamers,
        cardDeck: CardDeck,
        changeState: (Turn) -> Unit,
        askPlayerWantToStay: ((String) -> Boolean)?,
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
            winners = dealer.winners(players),
            losers = dealer.losers(players),
        )
    }
}
