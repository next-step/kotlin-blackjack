package blackjack.domain

class Player private constructor(val name: String) {
    var hands = Hands.from(PlayingCards.empty())
        private set
    var state = PlayerState.of(hands.score)
        private set

    fun receive(playingCards: PlayingCards) {
        hands += playingCards
        state = PlayerState.of(hands.score)
    }

    fun finish() {
        state = PlayerState.of(
            score = hands.score,
            isRunning = false
        )
    }

    fun isReceivable(): Boolean = !state.isFinished()

    companion object {
        fun from(name: String): Player {
            return Player(name)
        }
    }
}
