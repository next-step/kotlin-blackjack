package blackjack.domain.state

class Stay : Finished() {
    override fun profit(money: Double): Double {
        return money * 1
    }
}
