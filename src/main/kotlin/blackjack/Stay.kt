package blackjack

class Stay(cards: Cards) : Finished(cards) {
    override fun earningRate(): Double = 1.0
}
