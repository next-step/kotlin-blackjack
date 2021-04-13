package blackjack.model

data class Bet(private val amount: Int) {
    companion object {
        val ZERO = Bet(0)
    }
}
