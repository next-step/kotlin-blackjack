package blackjack

class Bust(cards: Cards) : Finished(cards) {
    override fun earningRate(): Double = -1.0
}
