package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

interface State {
    fun cards(): Cards

    fun receiveCard(card: Card): State
}
