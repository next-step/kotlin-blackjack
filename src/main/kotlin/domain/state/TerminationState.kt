package domain.state

import domain.card.BlackjackCard
import domain.card.BlackjackCards

open class TerminationState(private val cards: BlackjackCards) : State {
    override fun draw(card: BlackjackCard): State = this

    override fun stop(): State = this

    override fun getCards(): BlackjackCards = this.cards

    override fun isDrawable(): Boolean = false

    override fun isProceeding(): Boolean = false

    override fun getScoreSum(): Int = this.cards.sum
}
