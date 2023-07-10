package blackjack.domain.gamer

class Dealer : BlackJackGamer(name = DEALER_NAME) {
    override fun getGamerType(): GamerType {
        return GamerType.DEALER
    }

    fun winMoney(winMoney: Int) {
        super.takeMoney(winMoney)
    }

    fun loseMoney(loseMoney: Int) {
        super.takeMoneyOut(loseMoney)
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val CONDITION_TO_DEALER_DRAW_CARD = 16
    }
}
