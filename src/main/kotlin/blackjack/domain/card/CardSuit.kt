package blackjack.domain.card

sealed interface CardSuit {
    data object Diamond : CardSuit
    data object Heart : CardSuit
    data object Spade : CardSuit
    data object Club : CardSuit

    companion object {
        val all = listOf(Diamond, Heart, Spade, Club)
    }
}
