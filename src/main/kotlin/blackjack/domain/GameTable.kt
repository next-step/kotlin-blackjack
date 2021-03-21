package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Players

class GameTable(private val players: Players, private val cardDeck: CardDeck) {
    private val dealer = Dealer()

    fun proceedFirstRound(): UserInfo {
        dealer.drawAtFirst(cardDeck)
        players.drawAtFirst(cardDeck)
        return UserInfo(Pair(dealer, players))
    }
}