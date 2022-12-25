package blackjack.domain

class Player(val name: String, val cards: Cards) {
    private var _state: PlayerState = PlayerState.Play.Idle
    val state: PlayerState
        get() = _state

    constructor(name: String, cards: Set<Card>) : this(name, Cards(cards))
    init {
        require(name.isNotBlank()) { "name should not be blank" }
        require(cards.cards.size == 2) { "should have 2 cards" }

        if (cards.sum() == CardValue.BLACK_JACK_VALUE) {
            _state = PlayerState.Done.BlackJack
        }
    }
    fun hit(card: Card) {
        cards.add(card)
        val sum = cards.sum()

        _state = when {
            sum == CardValue.BLACK_JACK_VALUE -> PlayerState.Done.BlackJack
            sum < CardValue.BLACK_JACK_VALUE -> PlayerState.Play.Hit
            else -> PlayerState.Done.Burst
        }
    }

    fun stay() {
        _state = PlayerState.Done.Stay
    }

    fun isNotDone() = _state == PlayerState.Play.Idle || _state == PlayerState.Play.Hit
}
