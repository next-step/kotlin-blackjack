package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Score

sealed class GameState(val hands: Hands) {
    protected fun isEmpty(): Boolean = hands.isEmpty()
    protected fun score(): Score = hands.score()
    abstract fun isFinished(): Boolean
    abstract fun stay(): GameState
    abstract fun plus(cards: List<Card>): GameState
}

sealed class Running(hands: Hands) : GameState(hands) {
    override fun isFinished(): Boolean = false
    override fun stay(): GameState = Stay(hands)
}

class Started(hands: Hands = Hands.EMPTY) : Running(hands) {
    override fun plus(cards: List<Card>): GameState {
        val newHands = hands + cards
        if (newHands.isStart() && newHands.score().isMaxiMum()) {
            return BlackJack(newHands)
        }
        if (newHands.score().isBust()) {
            return Bust(newHands)
        }
        return Hit(newHands)
    }
}

class Hit(hands: Hands) : Running(hands) {
    override fun plus(cards: List<Card>): GameState {
        val newHands = hands + cards
        if (newHands.score().isBust()) {
            return Bust(newHands)
        }
        return Hit(newHands)
    }
}

sealed class Finish(hands: Hands) : GameState(hands) {
    override fun isFinished(): Boolean = true
    override fun stay(): GameState = throw IllegalArgumentException()
    override fun plus(cards: List<Card>): GameState = throw IllegalArgumentException()
}

class BlackJack(hands: Hands) : Finish(hands)
class Bust(hands: Hands) : Finish(hands)
class Stay(hands: Hands) : Finish(hands)
