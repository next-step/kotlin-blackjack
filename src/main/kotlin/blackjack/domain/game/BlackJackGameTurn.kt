package blackjack.domain.game

sealed class BlackJackGameTurn {

    object CardDistribution : BlackJackGameTurn()

    data class PlayerAnswer(val playerName: String) : BlackJackGameTurn()

    object Dealer : BlackJackGameTurn()

    object Finished : BlackJackGameTurn()

    fun hasNextTurn() = this !is Finished
}
