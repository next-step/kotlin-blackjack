package blackjack.domain.state

class Bust : Finished() {
    override fun profit(money: Double): Double {
        return money * 0
    }
}
