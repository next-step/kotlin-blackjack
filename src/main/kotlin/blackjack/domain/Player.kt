package blackjack.domain

class Player(val name: String, cards: PlayerCards) {
    var cards: PlayerCards = cards
        private set

    var state: States = States.HIT
        get() {
            return valueOf(cards.score, field)
        }

    val isPlaying: Boolean
        get() {
            return state == States.HIT
        }

    init {
        if (cards.score == Game.BLACK_JACK_SCORE) {
            state = States.BLACK_JACK
        }
    }

    constructor(name: String, firstCard: Card, secondCard: Card) : this(name, PlayerCards(firstCard, secondCard))

    private fun valueOf(score: Int, state: States): States {
        if (state == States.STAY || state == States.BLACK_JACK) {
            return state
        }

        return valueOf(score)
    }

    private fun valueOf(score: Int): States {
        if (score < Game.BLACK_JACK_SCORE) {
            return States.HIT
        } else if (score > Game.BLACK_JACK_SCORE) {
            return States.BUST
        }

        return States.STAY
    }

    fun draw(card: Card) {
        throwExceptionIfIsNotPlayingState()

        cards = cards.addCard(card)
    }

    fun stop() {
        state = States.STAY
    }

    fun throwExceptionIfIsNotPlayingState() {
        if (isPlaying.not()) {
            throw IllegalStateException("게임이 진행 불가능한 상태입니다. : $state")
        }
    }
}
