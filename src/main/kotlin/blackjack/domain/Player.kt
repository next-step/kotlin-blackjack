package blackjack.domain

data class Player(val name: String, var cards: Cards = Cards()) {
    fun hit(card: Card) {
        cards = cards.add(card)
    }

    fun isBust() = cards.getScore() >= TWENTY_ONE

    companion object {
        private const val TWENTY_ONE = 21
    }
}
