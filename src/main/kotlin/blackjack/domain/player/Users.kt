package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.GameResult
import blackjack.domain.UserInfo
import blackjack.domain.card.CardDeck

class Users(val users: List<User>) {
    fun drawAtFirst(cardDeck: CardDeck) {
        users.forEach { user ->
            repeat(FIRST_DRAW_COUNT) { user.draw(cardDeck.pop(), DrawDecider.DRAW) }
        }
    }

    fun draw(drawDecider: (User) -> DrawDecider, cardDeck: CardDeck) {
        drawPlayers(cardDeck, drawDecider)
        drawDealer(cardDeck, drawDecider)
    }

    fun getResult(): GameResult {
        return GameResult(dealer = users[0], players = users.drop(1))
    }

    private fun drawPlayers(cardDeck: CardDeck, drawDecider: (User) -> DrawDecider) {
        users.drop(1).forEach { it.draw(cardDeck.pop(), drawDecider(it)) }
    }

    private fun drawDealer(cardDeck: CardDeck, drawDecider: (User) -> DrawDecider) {
        val dealer = users[0] as Dealer
        dealer.drawAdditional(cardDeck, drawDecider(dealer))
    }

    fun toUserInfo(): UserInfo {
        return UserInfo(Pair(users[0] as Dealer, Users(users.drop(1))))
    }

    companion object {
        private const val FIRST_DRAW_COUNT = 2
    }
}
