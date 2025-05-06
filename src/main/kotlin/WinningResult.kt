import GameResult.Companion.getApposite
import GameResult.DRAW
import GameResult.LOSE
import GameResult.WIN
import participant.Participant
import state.Blackjack
import state.Bust
import state.Stay

class WinningResult {
    private val dealerResult: MutableMap<GameResult, Int> =
        mutableMapOf(
            WIN to 0,
            LOSE to 0,
            DRAW to 0,
        )

    fun getResult() = dealerResult.toMap()

    fun versus(
        dealer: Participant,
        player: Participant,
    ): GameResult {
        val result = determineResult(dealer, player)
        dealerResult[result] = dealerResult.getValue(result) + 1
        return getApposite(result)
    }

    private fun determineResult(
        dealer: Participant,
        player: Participant,
    ): GameResult {
        return when (dealer.state) {
            is Bust -> LOSE
            is Blackjack -> if (player.state is Blackjack) DRAW else WIN
            is Stay ->
                when {
                    dealer.score() < player.score() -> LOSE
                    dealer.score() == player.score() -> DRAW
                    else -> WIN
                }
            else -> throw IllegalStateException()
        }
    }
}
