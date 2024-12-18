package blackjack.domain.state

import blackjack.domain.card.PlayingCard

interface State {
    val cards: List<PlayingCard>
}
