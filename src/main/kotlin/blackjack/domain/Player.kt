package blackjack.domain

class Player(val name: String) {
    var cards: MutableList<Card> = mutableListOf()
        private set
    var score: Int = 0
        private set
    var canRace = true
        private set
    var winner = false
        private set

    fun addCard(card: Card) {
        cards.add(card)
        addScore(card)
    }

    fun addScore(card: Card) {
        score += card.cardValue.value
    }

    fun cantRace() {
        canRace = false
    }

    fun isWinner() {
        winner = true
    }

    fun cardsName(): List<String> {
        return cards.map {
            it.name()
        }
    }
}
