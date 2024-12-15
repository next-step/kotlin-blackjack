package blackjack.player

import betting.TurnResult
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
        dealer: Dealer,
    ): TurnResult {
        val (updatedPlayers, updatedDealer) =
            players.fold(initial = emptyList<Player>() to dealer) { (currentPlayers, currentDealer), player ->
                if (player.isBust()) {
                    return@fold currentPlayers + player to currentDealer
                }

                val playerAfterTurn = player.play(isHitCard = isHitCard(player), draw = draw).also(afterPlay)
                if (playerAfterTurn.isBust()) {
                    val newDealer = currentDealer.win(player = playerAfterTurn)
                    return@fold currentPlayers + playerAfterTurn.lose() to newDealer
                }

                return@fold currentPlayers + playerAfterTurn to currentDealer
            }

        return TurnResult.status(players = updatedPlayers, dealer = updatedDealer)
    }

    fun sum(): Double = players.sumOf { it.betAmount }

    fun applyWinToWinners(): Players = Players(players = players.map { if (it.isBust()) it else it.win() })

    companion object {
        fun generateFromNames(playerNames: List<String>) = Players(players = playerNames.map { Player.ready(name = it) })
    }
}
