package blackjack.domain

class Player(val name: String, cards: PlayerCards) {
    var cards: PlayerCards = cards
        private set

    var state: States = States.HIT
        get() {
            if (name == "dealer" && cards.score > 21) {
                return States.WIN
            }

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

    constructor(name: String, cards: Set<Card>) : this(name, PlayerCards(cards))
    constructor(name: String, vararg cards: Card) : this(name, PlayerCards(cards.toSet()))

    private fun findStateByScore(score: Int): States {
        return when {
            score < Game.BLACK_JACK_SCORE -> { States.HIT }
            score > Game.BLACK_JACK_SCORE -> { States.BUST }
            else -> { States.STAY }
        }
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

    fun isSmallerThanMinimumDealerScore(): Boolean {
        return cards.score <= 16
    }

    fun isEnd(): Boolean {
        if (name == "dealer") {
            return state == States.WIN
        }

        return state == States.BLACK_JACK
    }
}
