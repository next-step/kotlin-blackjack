package blackjack.domain

abstract class Participant(val name: String, cards: PlayerCards) {

    var cards: PlayerCards = cards
        private set

    var state: States = States.HIT
        get() {
            return findStateByScore(cards.score)
        }
        private set

    val isPlaying: Boolean
        get() {
            return state == States.HIT
        }

    abstract fun findStateByScore(score: Int): States

    abstract val isEnd: Boolean

    abstract fun isSmallerThanMinimumDealerScore(): Boolean

    fun draw(card: Card) {
        throwExceptionIfIsNotPlayingState()

        cards = cards.addCard(card)
    }

    fun throwExceptionIfIsNotPlayingState() {
        if (isPlaying.not()) {
            throw IllegalStateException("게임이 진행 불가능한 상태입니다. : $state")
        }
    }

    fun stop() {
        state = States.STAY
    }
}
