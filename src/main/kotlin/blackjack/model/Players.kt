package blackjack.model

class Players(val values: List<Player>) {

    fun initialCardDealing(dealer: Dealer) {
        values.forEach {
            it.addCards(dealer.dealingTwoCards())
        }
    }

    fun bettingMoney(setBettingMoney: (Player) -> Unit) {
        values.forEach {
            setBettingMoney.invoke(it)
        }
    }
}
