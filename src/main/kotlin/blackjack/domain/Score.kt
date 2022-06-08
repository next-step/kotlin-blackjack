package blackjack.domain

data class Score(val origin: Int, val alternative: Int) {

    fun couldGetMoreCard(): Boolean {
        return origin < BLACKJACK || alternative < BLACKJACK
    }

    fun max(): Int {
        return when {
            origin > BLACKJACK -> alternative
            alternative > BLACKJACK -> origin
            origin > alternative -> origin
            else -> alternative
        }
    }

    companion object {
        private const val BLACKJACK = 21
    }
}
