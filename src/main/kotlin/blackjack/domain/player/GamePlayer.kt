package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.rule.Money

class GamePlayer(
    override val name: String,
    override val cardHold: CardHold = CardHold(),
) : GameMember {
    override var money: Money = Money()
    override fun canDraw(): Boolean = cardHold.getTotalPoints() <= GameMember.THRESHOLD
}
