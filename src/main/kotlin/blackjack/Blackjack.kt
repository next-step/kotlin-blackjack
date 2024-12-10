package blackjack

class Blackjack(cards: Cards) : Finished(cards) {
    override fun earningRate(): Double = 1.5
}
