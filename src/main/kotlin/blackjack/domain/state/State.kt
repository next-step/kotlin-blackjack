package blackjack.domain.state

import blackjack.domain.card.Cards

interface State {
    fun cards(): Cards
}
