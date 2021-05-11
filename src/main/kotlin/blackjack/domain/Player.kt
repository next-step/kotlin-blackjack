package blackjack.domain

class Player(val name: String, cards: Cards) {
    var cards: Cards = cards
        private set

    var state: States = States.HIT
        get() {
            if (field == States.STAY || field == States.BLACK_JACK) {
                return field
            }

            return findStateByScore(cards.score)
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

    constructor(name: String, cards: Set<Card>) : this(name, Cards(cards))

    private fun findStateByScore(score: Int): States {
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

    private fun throwExceptionIfIsNotPlayingState() {
        if (isPlaying.not()) {
            throw IllegalStateException("게임이 진행 불가능한 상태입니다. : $state")
        }
    }
}
