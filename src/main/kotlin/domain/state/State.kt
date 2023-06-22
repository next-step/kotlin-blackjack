package domain.state

import domain.card.BlackjackCard
import domain.card.BlackjackCards

interface State {

    fun draw(card: BlackjackCard): State
    fun stop(): State
    fun getCards(): BlackjackCards
    fun isDrawable(): Boolean
    fun isProceeding(): Boolean

    fun getScoreSum(): Int
}
