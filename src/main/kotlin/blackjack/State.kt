package blackjack

interface State {
    fun draw(card: Card): State

    fun stay(): State

    fun isFinished(): Boolean

    fun cards(): Cards
}
