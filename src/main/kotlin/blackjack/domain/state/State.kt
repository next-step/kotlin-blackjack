package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

interface State {

    fun init(cards: List<Card>): State

    fun draw(card: Card): State

    fun stay(): State

    fun getSum(): Int

    fun cards(): Hand

    fun isFinished(): Boolean

    fun isBlackjack(): Boolean
}
