package blackjack.domain.gamer

import blackjack.domain.card.Card

sealed class DealerCard {

    data class Open(val card: Card) : DealerCard()

    object Hide : DealerCard()
}
