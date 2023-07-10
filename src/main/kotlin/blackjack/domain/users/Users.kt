package blackjack.domain.users

import blackjack.domain.BlackjackGame
import blackjack.domain.card.Cards
import blackjack.domain.result.GameResults

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
            return GameResults.ofDealerLose(players)
        }

        return GameResults.from(players, dealer)
    }
}
