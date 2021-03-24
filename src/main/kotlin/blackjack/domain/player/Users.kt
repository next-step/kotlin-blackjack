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

    fun doPlayers(action: (User) -> Unit) = users.drop(1).forEach(action)

    fun doDealer(action: (Dealer) -> Unit) = action

    fun getResult() = GameResult(dealer = users[0], players = users.drop(1))

    fun toUserInfo(): UserInfo {
        return UserInfo(Pair(users[0] as Dealer, Users(users.drop(1))))
    }

    companion object {
        private const val FIRST_DRAW_COUNT = 2
    }
}
