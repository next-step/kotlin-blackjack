package blackjack.domain

import blackjack.domain.Finished.Blackjack
import blackjack.domain.Finished.Bust
import blackjack.domain.Finished.Stay
import blackjack.domain.Playing.Hit
import blackjack.model.GameResult
import blackjack.model.PlayerGameResult
import blackjack.model.PlayerGameResults

class GameResultCalculator : ResultCalculator {

    override fun calculate(dealer: Dealer, players: Players): PlayerGameResults {
        val playerResults = calculatePlayerResult(dealer, players)
        val dealerResult = calculateDealerResult(dealer, playerResults)
        return PlayerGameResults(listOf(dealerResult) + playerResults)
    }

    private fun calculatePlayerResult(dealer: Dealer, players: Players): List<PlayerGameResult> {
        val playerResults = mutableListOf<PlayerGameResult>()
        players.value.map { player ->
            val result = calculateGameResult(dealer, player)
            playerResults.add(PlayerGameResult.Player(player.name, result))
        }
        return playerResults
    }

    private fun calculateDealerResult(dealer: Dealer, playerResults: List<PlayerGameResult>): PlayerGameResult.Dealer =
        PlayerGameResult.Dealer(
            name = dealer.name,
            win = playerResults.count { it is PlayerGameResult.Player && it.gameResult == GameResult.LOSE },
            push = playerResults.count { it is PlayerGameResult.Player && it.gameResult == GameResult.PUSH },
            lose = playerResults.count { it is PlayerGameResult.Player && it.gameResult == GameResult.WIN },
        )

    private fun calculateGameResult(dealer: Dealer, player: Player): GameResult {
        val playerSum = player.sumCards()
        val dealerSum = dealer.sumCards()
        return when {
            player.state.blackjack && dealer.state.blackjack -> GameResult.PUSH
            dealer.state.blackjack -> GameResult.LOSE
            player.state.blackjack || (dealer.state.bust && !player.state.bust) -> GameResult.WIN
            player.state.bust -> GameResult.LOSE
            playerSum == dealerSum -> GameResult.PUSH
            playerSum > dealerSum -> GameResult.WIN
            else -> GameResult.LOSE
        }
    }

    private val State.blackjack: Boolean
        get() = this is Blackjack
    private val State.bust: Boolean
        get() = this is Bust

    private val State.hit: Boolean
        get() = this is Hit

    private val State.stay: Boolean
        get() = this is Stay
}

interface ResultCalculator {
    fun calculate(dealer: Dealer, players: Players): PlayerGameResults
}
