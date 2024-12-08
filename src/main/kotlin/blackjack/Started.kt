package blackjack

sealed class Started(private val cards: Cards) : State {
    override fun cards(): Cards = cards
}
