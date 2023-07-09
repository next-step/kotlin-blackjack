package blackjack.domain.users

import blackjack.domain.BlackjackGame
import blackjack.domain.Cards
import blackjack.domain.result.DealerResult
import blackjack.domain.result.GameResults
import blackjack.domain.result.PlayerResult
import kotlin.math.roundToInt

data class Users(val players: List<Player>, val dealer: Dealer) {
    val userCards: Map<User, Cards>
        get() {
            val dealerCardMap = mapOf<User, Cards>(dealer to dealer.cards)
            return dealerCardMap.plus(players.associateBy<Player, User, Cards>({ it }, { it.cards }))
        }

    fun cardReceivePossibleUsers(): List<Player> {
        return players.filter { player -> player.isDeckInComplete() }
    }

    fun calculateGameResult(): GameResults {
        val dealerCardValue = dealer.cardValues()

        if (dealerCardValue > BlackjackGame.BLACKJACK_VALUE) {
            return dealerLose()
        }

        var gameResults = GameResults(emptyList(), DealerResult())
        players.forEach {
            gameResults = gameResult(it, dealerCardValue, gameResults)
        }
        return gameResults
    }

    private fun dealerLose() = GameResults(
        players.map { PlayerResult(it.name, it.bettingAmount) },
        DealerResult(players.minOf { it.bettingAmount })
    )

    private fun gameResult(
        player: Player,
        dealerCardValue: Int,
        gameResults: GameResults
    ): GameResults {
        val finalRevenue = player.bettingAmount
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return gameResults.playerWin(PlayerResult(player.name, finalRevenue.times(1.5).roundToInt()))
        }

        if (player.isBlackjack() && dealer.isBlackjack()) {
            return gameResults.playerWin(PlayerResult(player.name, 0))
        }

        if (player.cardValues() in (dealerCardValue + 1)..21) {
            return gameResults.playerWin(PlayerResult(player.name, finalRevenue))
        }
        return gameResults.playerLose(PlayerResult(player.name, finalRevenue.times(-1)))
    }
}
