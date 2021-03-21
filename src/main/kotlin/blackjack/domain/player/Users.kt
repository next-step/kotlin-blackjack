package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.UserInfo
import blackjack.domain.card.CardDeck

class Users(val users: List<User>) {
    fun drawAtFirst(cardDeck: CardDeck) {
        users.forEach { user ->
            repeat(FIRST_DRAW_COUNT) { user.draw(cardDeck.pop(), DrawDecider.DRAW) }
        }
    }

    fun draw(drawDecider: (User) -> DrawDecider, cardDeck: CardDeck) {
        users.forEach { it.draw(cardDeck.pop(), drawDecider(it)) }
    }

    fun toUserInfo(): UserInfo {
        return UserInfo(Pair(users[0] as Dealer, Users(users.drop(1))))
    }

    companion object {
        private const val FIRST_DRAW_COUNT = 2
    }
}
