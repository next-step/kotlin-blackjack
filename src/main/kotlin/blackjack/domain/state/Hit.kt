package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Hands

class Hit(override val hands: Hands) : State {
    override fun draw(cards: Set<Card>): State {
        hands.addAll(cards)
        return when{
            hands.blackJack() -> BlackJack(hands)
            hands.bust() -> Bust(hands)
            else -> this
        }
    }
}
