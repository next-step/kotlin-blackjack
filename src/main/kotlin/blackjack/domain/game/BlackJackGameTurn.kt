package blackjack.domain.game

import blackjack.domain.player.PlayerName

sealed class BlackJackGameTurn {

    object CardDistribution : BlackJackGameTurn()

    data class HitOrStay(
        val playerName: PlayerName,
    ) : BlackJackGameTurn()

    object Finished : BlackJackGameTurn()

    fun isFinished() = this is Finished
}
