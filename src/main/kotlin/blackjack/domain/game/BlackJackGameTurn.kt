package blackjack.domain.game

import blackjack.domain.player.PlayerName

sealed class BlackJackGameTurn {

    object CardDistributionWait : BlackJackGameTurn()

    data class HitAnswerWait(
        val playerName: PlayerName,
    ) : BlackJackGameTurn()

    object Finished : BlackJackGameTurn()

    fun isFinished() = this is Finished
}
