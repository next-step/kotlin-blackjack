package blackjack.domain.gamer

import blackjack.domain.card.Card

sealed class CardHolder {

    data class Open(val card: Card) : CardHolder()

    object Hide : CardHolder()
}
