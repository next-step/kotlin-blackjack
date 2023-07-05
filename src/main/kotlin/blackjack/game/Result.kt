package blackjack.game

data class Result(
    val earnings: Int = 0
) {
    val formattedEarnings: String
        get() = earnings.toString()
}
