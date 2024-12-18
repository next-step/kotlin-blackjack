package blackjack.player

import betting.BetResult
import blackjack.card.Card
import blackjack.dealer.Dealer

class Players(
    val players: List<Player>,
) {
    fun add(newPlayers: Players) = Players(players = newPlayers.players.plus(this.players))

    fun getRemainedPlayers() = Players(players = players.filterNot { it.isBust() })

    fun play(
        isHitCard: (Player) -> Boolean,
        draw: () -> Card,
        afterPlay: (Player) -> Unit,
    ): Players =
        Players(
            players.map { it.play(isHitCard = isHitCard(it), draw = draw).also(afterPlay) },
        )

    fun sum(): Double = players.sumOf { it.betAmount }

    fun applyWinToRemainPlayer(dealer: Dealer): Players {
        if(dealer.isBust()) {
            return Players(players = players.map { if (it.isBust()) it else it.win() })
        }

        return this
    }

    fun getLosers(): List<Player> = players.filter { it.betResult is BetResult.Lose }

    companion object {
        fun generateFromNames(playerNames: List<String>) = Players(players = playerNames.map { Player.ready(name = it) })
    }
}
