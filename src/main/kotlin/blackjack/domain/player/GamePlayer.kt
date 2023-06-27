package blackjack.domain.player

import blackjack.domain.card.CardHold

class GamePlayer(
    override val name: String,
    override val cardHold: CardHold = CardHold()
) : GameMember {
    override fun canDraw(): Boolean = cardHold.getTotalPoints() <= GameMember.THRESHOLD
}
