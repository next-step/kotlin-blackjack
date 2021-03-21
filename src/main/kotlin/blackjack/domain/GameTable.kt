package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Players
import blackjack.domain.player.User

class GameTable(private val players: Players, private val cardDeck: CardDeck) {
    private val dealer = Dealer()

    fun prepareGame(result: (UserInfo) -> Unit) {
        dealer.drawAtFirst(cardDeck)
        players.drawAtFirst(cardDeck)
        result(UserInfo(Pair(dealer, players)))
    }

    fun proceedGame(drawDecider: (User) -> DrawDecider, result: (UserInfo) -> Unit) {
        players.all {
            val decider = drawDecider(it)
            it.draw(cardDeck.pop(), decider)
        }
        val decider = drawDecider(dealer)
        dealer.draw(cardDeck.pop(), decider)
        result(UserInfo(Pair(dealer, players)))
    }

    fun endGame(result: (UserInfo) -> Unit) {
        result(UserInfo(Pair(dealer, players)))
    }
}