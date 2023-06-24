package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackjackGame(val players: Players) {
    val deck: CardDeck = CardDeck()

    fun initPlayers() {
        players.receiveCard { deck.getRandomCards(INIT_CARD_COUNT) }
    }

    fun dealCard(player: Player) {
        val card = deck.getRandomCard()
        player.receiveCard(card)
    }

    companion object {
        const val INIT_CARD_COUNT = 2
    }
}
