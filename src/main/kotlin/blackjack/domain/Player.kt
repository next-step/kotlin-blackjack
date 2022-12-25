package blackjack.domain

class Player(val name: String, val cards: Cards) {
    private var state: PlayerState = PlayerState.Play.Idle

    init {
        require(name.isNotBlank()) { "name should not be blank" }
        require(cards.cards.size == 2) { "should have 2 cards" }
    }
}
