package blackjack

sealed class Running(cards: Cards) : Started(cards) {
    override fun isFinished(): Boolean = false
}
