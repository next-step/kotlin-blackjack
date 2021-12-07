package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerList
import blackjack.domain.player.PlayerResult

class BlackjackGame(private val playerList: PlayerList) {

    init {
        playerList.getGamerList().forEach { addCardToPlayer(it, INITIAL_CARDS_NUMBER) }
        addCardToDealer(INITIAL_CARDS_NUMBER)
    }

    fun getGamerList() = playerList.getGamerList()

    fun getDealer() = playerList.dealer

    fun isDealerCardAddable() = playerList.dealer.isHandAddable()

    fun addCardToDealer(numberOfCards: Int = 1) =
        repeat(numberOfCards) { playerList.addCardToDealer(CardDeck.getNextCard()) }

    fun addCardToPlayer(player: Player, numberOfCards: Int = 1) =
        repeat(numberOfCards) { player.addCardToHand(CardDeck.getNextCard()) }

    fun getGameResultList(): List<PlayerResult> = playerList.getGameResultList()

    companion object {
        private const val INITIAL_CARDS_NUMBER = 2
    }
}
