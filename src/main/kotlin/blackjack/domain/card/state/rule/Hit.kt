package blackjack.domain.card.state.rule

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.Running
import blackjack.domain.card.state.State

class Hit(override var cards: PlayingCards) : Running(cards) {
    override fun draw(playingCard: PlayingCard): State {
        cards = cards.add(playingCard)
        if (cards.isBust()) {
            return Bust(cards)
        }
        return Hit(cards)
    }

    override fun stay(): State {
        return Stay(cards)
    }
}
