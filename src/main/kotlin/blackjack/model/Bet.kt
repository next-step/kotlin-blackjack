package blackjack.model

data class Bet(val amount: Int) {
    companion object {
        val ZERO = Bet(0)
    }
}
