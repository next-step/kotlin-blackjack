package blackjack.domain

class Player(val name: String) {
    var score: Int = 0
        private set
    var canRace = true
        private set
    var winner = false
        private set

    fun addScore(card: Card) {
        score += card.cardValue.ordinal
    }

    fun cantRace() {
        canRace = false
    }

    fun isWinner() {
        winner = true
    }
}
