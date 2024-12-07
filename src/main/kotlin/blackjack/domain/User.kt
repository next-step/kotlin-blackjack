package blackjack.domain

data class User(
    val name: String,
    val cards: Cards,
) {
    fun canHit(): Boolean {
        return cards.isScoreLowerThanLimit()
    }

    fun hit(card: Card): User {
        return this.copy(cards = cards.add(card))
    }

    companion object {
        fun create(name: String): User {
            return User(name, Cards(emptyList()))
        }
    }
}
