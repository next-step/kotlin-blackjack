package blackjack.domain

interface Participant {

    fun setBetMoney(amount: Int)

    fun draw(newCard: Card)

    fun getStateBy(reply: String = NO): State

    fun isHit(state: State) = state == State.HIT

    fun isBust(state: State): Boolean = state == State.BUST

    fun isBlackJack(state: State) = state == State.BLACKJACK

    fun score(): Int

    fun cardCount(): Int

    fun stateOfCards(): String
}
