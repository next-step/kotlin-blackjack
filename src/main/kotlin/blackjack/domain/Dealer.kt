package blackjack.domain

import blackjack.view.PlayerStatus

class Dealer(
    private val deck: Deck = Deck(),
    private val players: Array<Player>
) : Player(name = "딜러") {

    fun initializeRound() {
        val initDealerCards = arrayOf(deck.drawCard(), deck.drawCard())
        this.hit(*initDealerCards)
        for (player in players) {
            val firstRoundCards = arrayOf(deck.drawCard(), deck.drawCard())
            player.hit(*firstRoundCards)
        }
    }

    fun getAllPlayerStatus(): List<PlayerStatus> {
        val playerStatus = mutableListOf(PlayerStatus.of(this))
        for (player in players) {
            playerStatus.add(PlayerStatus.of(player))
        }

        return playerStatus
    }

    fun draw(): PokerCard {
        return deck.drawCard()
    }

    fun drawAdditionalCard(dealerAddNotice: () -> Unit) {
        if (this.optimalValue() <= DEALER_MIN_VALUE_TO_DRAW) {
            this.hit(this.draw())
            dealerAddNotice.invoke()
        }
    }

    companion object {
        private const val DEALER_MIN_VALUE_TO_DRAW = 16
    }
}
