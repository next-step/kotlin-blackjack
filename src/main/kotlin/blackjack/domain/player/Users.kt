package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.GameResult
import blackjack.domain.card.CardDeck

class Users(val users: List<User>) {
    fun drawAtFirst(cardDeck: CardDeck) {
        users.forEach { user ->
            repeat(FIRST_DRAW_COUNT) { user.draw(cardDeck.pop(), DrawDecider.DRAW) }
        }
    }

    fun draw(cardDeck: CardDeck, drawDecider: (User) -> DrawDecider, action: (User) -> Unit) {
        drawPlayers(cardDeck, drawDecider, action)
        drawDealer(cardDeck, drawDecider, action)
    }

    fun getResult(): GameResult {
        return GameResult(dealer = users[0], players = users.drop(1))
    }

    private fun drawPlayers(cardDeck: CardDeck, drawDecider: (User) -> DrawDecider, action: (User) -> Unit) {
        users.drop(1).forEach { it.draw(cardDeck.pop(), drawDecider(it)); action(it) }
    }

    private fun drawDealer(cardDeck: CardDeck, drawDecider: (User) -> DrawDecider, action: (User) -> Unit) {
        val dealer = users[0] as Dealer
        dealer.drawAdditional(cardDeck, drawDecider(dealer))
        action(dealer)
    }

    fun toUserInfo(): UserInfo {
        return UserInfo(Pair(users[0] as Dealer, Users(users.drop(1))))
    }

    companion object {
        private const val FIRST_DRAW_COUNT = 2
    }
}
