package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.User
import blackjack.domain.player.UserInfo
import blackjack.domain.player.Users

class GameTable(players: List<Player>, private val cardDeck: CardDeck) {
    private val users = Users(listOf(Dealer()) + players)

    fun prepareGame(result: (UserInfo) -> Unit) {
        users.drawAtFirst(cardDeck)
        result(users.toUserInfo())
    }

    fun proceedGame(drawDecider: (User) -> DrawDecider, action: (User) -> Unit, result: (UserInfo) -> Unit) {
        users.draw(cardDeck, drawDecider, action)
        result(users.toUserInfo())
    }

    fun endGame(result: (GameResult) -> Unit) {
        result(users.getResult())
    }
}
