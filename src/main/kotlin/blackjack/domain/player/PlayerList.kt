package blackjack.domain.player

import blackjack.domain.card.Card

class PlayerList private constructor(private val gamerList: List<Player>, val dealer: Dealer = Dealer()) {

    fun getGamerList() = gamerList.toList()

    fun addCardToDealer(card: Card) {
        dealer.addCardToHand(card)
    }

    fun getGameResultList(): List<PlayerResult> {
        val dealerHandValue = dealer.getMakeableValues().maxOrNull() ?: 0
        val isDealerBusted = dealer.isBusted()
        setGamerResult(dealerHandValue, isDealerBusted)
        setDealerResult()
        val playerResultList: MutableList<PlayerResult> = gamerList
            .map { PlayerResult(it.getBetResult(dealerHandValue, isDealerBusted), it.status) }.toMutableList()
        val dealerBetResult = playerResultList.sumOf { it.betResult.value } * -1
        playerResultList.add(0, PlayerResult(BetResult(dealerBetResult), dealer.status))
        return playerResultList.toList()
    }

    private fun Player.getBetResult(dealerHandValue: Int, isDealerBusted: Boolean): BetResult {
        val betValue = status.gameStatus.bet.value
        val playerHandValue = this.getMakeableValues().maxOrNull() ?: 0
        val isPlayerAndDealerHandValueSame = dealerHandValue == playerHandValue
        val isPlayerWin = playerHandValue > dealerHandValue
        val betRatio = getBetRation(
            isDealerBusted,
            isBlackJackHand(),
            isPlayerWin,
            isPlayerAndDealerHandValueSame,
            getCards().size
        )
        return BetResult((betValue * betRatio).toInt())
    }

    private fun getBetRation(
        dealerBusted: Boolean,
        playerBlackJack: Boolean,
        playerWin: Boolean,
        sameHandValue: Boolean,
        handSize: Int
    ): Double {
        if (playerBlackJack && playerWin && handSize == 2)
            return FIRST_HAND_BLACK_JACK_AND_WIN_RATIO
        if (playerBlackJack && sameHandValue)
            return FIRST_HAND_BLACK_JACK_DRAW_RATIO
        if (dealerBusted || playerWin)
            return WIN_BET_RATIO
        if (sameHandValue)
            return DRAW_BET_RATIO
        return LOSE_BET_RATIO
    }

    private fun setDealerResult() {
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

    private fun setGamerResult(dealerHandValue: Int, isDealerBusted: Boolean) {
        gamerList
            .forEach { it.setResultByDealerScore(dealerHandValue, isDealerBusted) }
    }

    companion object {
        const val FIRST_HAND_BLACK_JACK_AND_WIN_RATIO = 2.5
        const val FIRST_HAND_BLACK_JACK_DRAW_RATIO = 1.5
        const val WIN_BET_RATIO = 1.0
        const val LOSE_BET_RATIO = -1.0
        const val DRAW_BET_RATIO = 0.0

        fun createPlayerList(playerNames: List<PlayerName>, bets: List<Bet>) =
            createPlayerList(playerNames, bets, Dealer())

        fun createPlayerList(playerNames: List<PlayerName>, bets: List<Bet>, dealer: Dealer): PlayerList {
            val gamerList = playerNames.zip(bets).map { (name, bet) -> Gamer(name, bet = bet) }
            return PlayerList(gamerList, dealer)
        }
    }
}
