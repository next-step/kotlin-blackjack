package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerList
import blackjack.domain.player.PlayerResult

class BlackjackGame(private val playerList: PlayerList) {

    init {
        playerList.getList().forEach { addCardToPlayer(it, INITIAL_CARDS_NUMBER) }
        addCardToPlayer(playerList.dealer, INITIAL_CARDS_NUMBER)
    }

    fun getPlayerList() = playerList.getList()

    fun getDealer() = playerList.dealer

    fun isDealerCardAddable() = playerList.dealer.isHandAddable()

    fun addCardToDealer(numberOfCards: Int = 1) =
        repeat(numberOfCards) { playerList.dealer.addCardToHand(CardDeck.getNextCard()) }

    fun addCardToPlayer(player: Player, numberOfCards: Int = 1) =
        repeat(numberOfCards) { player.addCardToHand(CardDeck.getNextCard()) }

    fun getResults(): List<PlayerResult> {
        val dealerHandValue = playerList.dealer.getMaxHandValue()
        val isDealerBusted = playerList.dealer.isBusted()
        setPlayerResult(dealerHandValue, isDealerBusted)
        setDealerResult()
        return getGameResultList()
    }

    private fun getGameResultList(): List<PlayerResult> {
        val playerResultList = mutableListOf<PlayerResult>()
        playerResultList.add(PlayerResult(getDealer().status))
        playerList
            .getList()
            .forEach { playerResultList.add(PlayerResult(it.status)) }
        return playerResultList.toList()
    }

    private fun setPlayerResult(dealerHandValue: Int, isDealerBusted: Boolean) {
        playerList
            .getList()
            .forEach { it.setResultByDealerScore(dealerHandValue, isDealerBusted) }
    }

    private fun setDealerResult() {
        val isDealerBusted = playerList.dealer.isBusted()
        if (isDealerBusted) {
            playerList.dealer.countUpLose(playerList.getSize())
            return
        }
        val playerWins = playerList.getList()
            .sumOf { it.getWins() }
        val playerLoses = playerList.getList()
            .sumOf { it.getLoses() }
        playerList.dealer.apply {
            countUpWin(playerLoses)
            countUpLose(playerWins)
        }
    }

    companion object {
        private const val INITIAL_CARDS_NUMBER = 2
    }
}
