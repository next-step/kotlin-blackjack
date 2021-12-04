package blackjack.domain

import blackjack.controller.GameController.BLACK_JACK_SCORE

open class Player(open val name: Name, open val cards: Cards = Cards.from(emptyList())) {

    val score: Score
        get() = cards.getScore()

    fun isBust(): Boolean = cards.getScore().isBust()

    fun hit(card: Card) {
        if (canHit()) {
            cards.addCard(card)
        }
    }

    fun copy() = Player(name, cards)

    open fun result(other: Player): GameResultState {
        if (other.isBust()) return GameResultState.Win
        if (isBust()) return GameResultState.Lose
        return when {
            score > other.score -> {
                GameResultState.Win
            }
            score < other.score -> {
                GameResultState.Lose
            }
            else -> {
                GameResultState.Draw
            }
        }
    }

    open fun canHit() = cards.getScore() < Score.from(BLACK_JACK_SCORE)

    override fun toString(): String {
        return "Player(name=$name, cards=$cards)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (name != other.name) return false
        if (cards != other.cards) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode() + cards.hashCode()
        result *= 31
        return result
    }

    companion object {
        fun of(name: Name, cards: Cards = Cards.from(emptyList())): Player {
            return Player(name, cards)
        }
    }
}
