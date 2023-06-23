package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackjackGame(val players: Players) {
    val deck: CardDeck = CardDeck()

    fun initPlayers() {
        players.value.forEach { player ->
            val cards = deck.getRandomCards(INIT_CARD_COUNT)
            cards.value.forEach { card -> player.receiveCard(card) }
        }
    }

    fun dealCards(player: Player) {
        val card = deck.getRandomCards(SHARE_CARD_COUNT).value.first()
        player.receiveCard(card)
    }

    companion object {
        private const val SHARE_CARD_COUNT = 1
        const val INIT_CARD_COUNT = 2
    }
}
