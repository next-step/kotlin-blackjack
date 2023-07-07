package baclkjack.domain.play

import baclkjack.domain.card.Deck

class Player(override val name: String, override val cards: Cards = Cards()) : User {

    var cardDrawListener: CardDrawListener? = null

    override fun start(deck: Deck) {
        repeat(FIRST_DRAW) {
            cards.add(deck.draw())
        }
    }

    override fun hit(deck: Deck) {
        cards.add(deck.draw())
    }

    override fun burst(): Boolean = cards.isBurst()

    override fun blackJack(): Boolean = cards.isBlackJack()

    override fun score(): Int = cards.score()

    override fun isDraw(): Boolean = cardDrawListener?.isDraw(name) == true

    fun result(user: User): GameState = ofGameState(user)

    fun ofGameState(user: User): GameState {
        return when {
            this.burst() -> GameState.LOSE
            user.burst() -> GameState.WIN
            this.score() > user.score() -> GameState.WIN
            this.score() < user.score() -> GameState.LOSE
            else -> GameState.DRAW
        }
    }

    companion object {
        const val FIRST_DRAW = 2
    }
}

