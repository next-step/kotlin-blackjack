package blackjack.model.player

import blackjack.model.Money
import blackjack.model.card.PlayerCards
import blackjack.view.OutputView

const val DEALER_NAME = "딜러"
const val DEALER_CALL_MAX_POINT = 16
const val DEALER_CALL_MAX_CARD = 3

class Dealer(override val name: String = DEALER_NAME) : Player {
    override var cards = PlayerCards()
    override var money = Money()

    override fun call(): Boolean {
        val isCall = cards.getPoint() <= DEALER_CALL_MAX_POINT && cards.getCount() < DEALER_CALL_MAX_CARD
        if (isCall) {
            OutputView.printDealerCall()
        }
        return isCall
    }

    fun checkPrize(players: List<Player>) {
        val gamers = players.filterIsInstance<Gamer>()
        gamers.forEach {
            val prizeRate = it.getPrizeRate(this)
            val earnMoney = it.money.calculate(prizeRate)

            it.money.add(earnMoney)
            this.money.subtract(earnMoney)
        }
    }
}
