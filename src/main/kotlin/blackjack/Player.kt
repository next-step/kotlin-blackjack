package blackjack

class Player(val cards: MutableList<Card> = mutableListOf(), var score: Int = INIT_SCORE) {
    companion object {
        private const val INIT_SCORE = 0
    }
}
