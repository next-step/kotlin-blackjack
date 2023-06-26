package blackjack.domain.card

sealed class CardHolder {

    data class Open(val card: Card) : CardHolder()

    object Hide : CardHolder()
}
