package blackjack.domain

data class Score(val value: Int) {
    init {
        require(value >= 1)
    }
}
