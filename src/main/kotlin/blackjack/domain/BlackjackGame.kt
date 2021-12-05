package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerList

class BlackjackGame(private val playerList: PlayerList) {
    private val cardDeck: CardDeck = CardDeck()

    init {
        playerList.getList().forEach { addCardToPlayer(it, INITIAL_CARDS_NUMBER) }
    }

    fun getPlayerList() = playerList.getList()

    fun addCardToPlayer(player: Player, numberOfCards: Int = 1) =
        repeat(numberOfCards) { player.addCardToHand(cardDeck.getNextCard()) }

    companion object {
        private const val INITIAL_CARDS_NUMBER = 2
    }
}
