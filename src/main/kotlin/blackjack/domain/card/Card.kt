package blackjack.domain.card

data class Card(val pattern: String, val number: String) {

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val ACE_DEFAULT_SCORE = 11
        const val ACE_SCORE = 1
        const val JACK_SCORE = 10
        const val QUEEN_SCORE = 10
        const val KING_SCORE = 10
    }
}
