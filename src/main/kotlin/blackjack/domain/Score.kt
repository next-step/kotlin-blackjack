package blackjack.domain

data class Score(val number: Int){
    val isBust = number > BLACKJACK_SCORE
    val stay = number < BLACKJACK_SCORE

    companion object {
        const val BLACKJACK_SCORE = 21
    }
}
