package blackjack.domain

data class Player(val name: String, val cards: Cards = Cards()) {

    companion object {
        fun of(name: String, cards: Cards = Cards()): Player {
            return Player(name, cards)
        }
    }
}
