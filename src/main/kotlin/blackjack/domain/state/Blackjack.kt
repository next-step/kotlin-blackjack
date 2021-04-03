package blackjack.domain.state

class Blackjack : Finished() {
    override fun profit(money: Double): Double {
        return money * 1.5
    }
}
