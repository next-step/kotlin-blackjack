package blackjack.domain.game

import blackjack.domain.gamer.PlayerName

sealed class BlackJackGameTurn {

    object CardDistribution : BlackJackGameTurn()

    data class PlayerAnswer(val playerName: PlayerName) : BlackJackGameTurn()

    object Dealer : BlackJackGameTurn()

    object Finished : BlackJackGameTurn()

    fun hasNextTurn() = this !is Finished
}
