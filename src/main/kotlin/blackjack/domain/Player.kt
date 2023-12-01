package blackjack.domain

data class Player(val name: String, val hand: Hand = Hand()) {
    var state: PlayerState = PlayerState.READY
        private set

    val resultScore: Score by lazy { decideScore() }

    fun initialize(deck: Deck) {
        check(state == PlayerState.READY) { "can only 'init' if the 'PlayerState' is 'READY'" }
        repeat(Rule.INIT_CARD_COUNT) { hand.add(deck.draw()) }
        state = PlayerState.UNDER
    }

    fun hit(deck: Deck) {
        check(active()) { "can only 'hit' if the 'PlayerState' is 'UNDER'" }
        hand.add(deck.draw())
        updateState()
    }

    fun stay() {
        check(active()) { "can only 'stay' if the 'PlayerState' is 'UNDER'" }
        state = PlayerState.STAND
    }

    fun updateState() {
        val score = hand.getBestScore()
        val count = hand.getCardCount()
        state = when {
            score.value < Rule.BLACKJACK_SCORE -> PlayerState.UNDER
            score.value > Rule.BLACKJACK_SCORE -> PlayerState.BUST
            else -> when (count == Rule.BLACKJACK_CARD_COUNT) {
                true -> PlayerState.BLACKJACK
                false -> PlayerState.STAND
            }
        }
    }

    fun ready() = state == PlayerState.READY

    fun active() = state == PlayerState.UNDER

    fun resolved() = state == PlayerState.STAND || state == PlayerState.BLACKJACK || state == PlayerState.BUST

    private fun decideScore(): Score {
        check(resolved()) { "can't decide score until the action is over" }
        return hand.getBestScore()
    }
}

@JvmInline
value class Players(val values: List<Player>) {

    constructor(vararg names: String) : this(names.map { Player(it) })

    init {
        require(values.size == values.map { it.name }.toSet().size) { "duplicate name has been used" }
    }
}

enum class PlayerState {
    READY,
    UNDER,
    STAND,
    BLACKJACK,
    BUST;
}
