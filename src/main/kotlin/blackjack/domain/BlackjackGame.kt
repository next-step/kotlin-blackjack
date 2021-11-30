package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.card.ShuffledCardDeck
import blackjack.domain.user.User

class BlackjackGame(playerNames: List<String>) {
    private val playerList: List<User>
    private val shuffledCardDeck: ShuffledCardDeck

    init {
        playerList = playerNames.map { User(it) }
        shuffledCardDeck = CardDeck.getShuffledCardDeck()
        playerList.forEach {
            addCardToPlayer(it, INITIAL_CARDS_NUMBER)
        }
    }

    fun getPlayerList() = playerList.toList()

    fun addCardToPlayer(user: User, numberOfCards: Int = 1) =
        repeat(numberOfCards) { user.addCardToHand(shuffledCardDeck.getNextCard()) }

    companion object {
        private const val INITIAL_CARDS_NUMBER = 2
    }
}
