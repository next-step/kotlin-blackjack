package blackjack.domain

import kotlin.math.abs

abstract class Participant(val name: String, cards: PlayerCards) {

    var cards: PlayerCards = cards
        private set

    var state: States = States.HIT
        get() {
            if (field == States.STAY || field == States.BLACK_JACK) {
                return field
            }

            return findStateByScore(cards.score)
        }
        private set

    var result: States = States.LOSE

    val isPlaying: Boolean
        get() {
            return state == States.HIT
        }

    abstract val isEnd: Boolean

    abstract fun findStateByScore(score: Int): States

    abstract fun isSmallerThanMinimumScore(): Boolean

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

    fun lose() {
        result = States.LOSE
    }

    fun win() {
        result = States.WIN
    }

    fun calculateToFindWinner(): Int {
        return abs(Game.BLACK_JACK_SCORE - cards.score)
    }
}
