package blackjack.domain.card.state.rule

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.Running
import blackjack.domain.card.state.State

class Hit(cards: PlayingCards) : Running(cards) {
    override fun draw(playingCard: PlayingCard): State {
        val newCards = cards + playingCard
        if (newCards.isBust()) {
            return Bust(newCards)
        }
        return Hit(newCards)
    }

    override fun stay(): State {
        return Stay(cards)
    }
}
