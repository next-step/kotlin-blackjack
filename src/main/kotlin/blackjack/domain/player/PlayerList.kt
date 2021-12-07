package blackjack.domain.player

import blackjack.domain.card.Card

class PlayerList private constructor(private val gamerList: List<Player>, val dealer: Dealer = Dealer()) {

    fun getGamerList() = gamerList.toList()

    fun addCardToDealer(card: Card) {
        dealer.addCardToHand(card)
    }

    fun getGameResultList(): List<PlayerResult> {
        val dealerHandValue = dealer.getMaxHandValue()
        val isDealerBusted = dealer.isBusted()
        setGamerResult(dealerHandValue, isDealerBusted)
        setDealerResult()
        val playerResultList = mutableListOf<PlayerResult>()
        playerResultList.add(PlayerResult(dealer.status))
        gamerList
            .forEach { playerResultList.add(PlayerResult(it.status)) }
        return playerResultList.toList()
    }

    fun setDealerResult() {
        val isDealerBusted = dealer.isBusted()
        if (isDealerBusted) {
            dealer.countUpLose(gamerList.size)
            return
        }
        val playerWins = gamerList
            .sumOf { it.getWins() }
        val playerLoses = gamerList
            .sumOf { it.getLoses() }
        dealer.apply {
            countUpWin(playerLoses)
            countUpLose(playerWins)
        }
    }

    fun setGamerResult(dealerHandValue: Int, isDealerBusted: Boolean) {
        gamerList
            .forEach { it.setResultByDealerScore(dealerHandValue, isDealerBusted) }
    }

    companion object {
        fun createPlayerList(playerNames: List<PlayerName>) = createPlayerList(playerNames, Dealer())

        fun createPlayerList(playerNames: List<PlayerName>, dealer: Dealer) =
            PlayerList(playerNames.map { Gamer(it) }, dealer)
    }
}
