package blackjack.domain.users

import blackjack.domain.Cards
import blackjack.domain.result.DealerResult
import blackjack.domain.result.GameResults
import blackjack.domain.result.PlayerResult

data class Users(val players: List<Player>, val dealer: Dealer) {
    val userCards: Map<User, Cards>
        get() {
            val dealerCardMap = mapOf<User, Cards>(dealer to dealer.cards)
            return dealerCardMap.plus(players.associateBy<Player, User, Cards>({ it }, { it.cards }))
        }

    fun cardReceivePossibleUsers(): List<Player> {
        return players.filter { player -> player.isDeckInComplete() }
    }

    fun dealerCardValue(): Int {
        return dealer.cardValues()
    }

    fun calculateGameResult(dealerCardValue: Int): GameResults {
        var gameResults = GameResults(emptyList(), DealerResult())
        players.forEach {
            gameResults = gameResult(it, dealerCardValue, gameResults)
        }
        return gameResults
    }

    private fun gameResult(
        player: Player,
        dealerCardValue: Int,
        gameResults: GameResults
    ): GameResults {
        if (player.cardValues() in (dealerCardValue + 1)..21) {
            return gameResults.playerWin(PlayerResult(player.name, true))
        }
        return gameResults.playerLose(PlayerResult(player.name, false))
    }
}
