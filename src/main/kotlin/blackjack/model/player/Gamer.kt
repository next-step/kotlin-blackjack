package blackjack.model.player

import blackjack.model.Money
import blackjack.model.card.Cards
import blackjack.view.InputView

class Gamer(override val name: String) : Player {
    override var cards = Cards(listOf())
    override lateinit var winLoseResult: String
    lateinit var betMoney: Money

    override fun call(): Boolean {
        while (continueToTurn() && InputView.askToDraw(this)) {
            return true
        }
        return false
    }

    override fun checkWinOrLose(players: List<Player>) {
        val dealer = players.filterIsInstance<Dealer>()[0]
        val isWin = isWin(dealer.cards)

        winLoseResult = if (isWin) WIN_TEXT else LOSE_TEXT
    }

    private fun isWin(dealerCards: Cards): Boolean {
        val dealerPoint = dealerCards.getBlackjackPoint()
        val gamerPoint = cards.getBlackjackPoint()

        if (dealerPoint > BLACKJACK_MAX_NUMBER) {
            return true
        }
        if (gamerPoint > BLACKJACK_MAX_NUMBER) {
            return false
        }
        return gamerPoint > dealerPoint
    }

    private fun continueToTurn(): Boolean {
        return cards.sumByPoint() < BLACKJACK_MAX_NUMBER
    }
}
