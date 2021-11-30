package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.card.ShuffledCardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerList

class BlackjackGame(private val playerList: PlayerList) {
    private val shuffledCardDeck: ShuffledCardDeck = CardDeck.getShuffledCardDeck()

    init {
        playerList.forEach {
            addCardToPlayer(it, INITIAL_CARDS_NUMBER)
        }
    }

    fun getPlayerList() = playerList.toList()

    fun addCardToPlayer(player: Player, numberOfCards: Int = 1) =
        repeat(numberOfCards) { player.addCardToHand(shuffledCardDeck.getNextCard()) }

    companion object {
        private const val INITIAL_CARDS_NUMBER = 2
    }
}
