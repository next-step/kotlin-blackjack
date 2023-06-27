package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.rule.Score

class PlayerImpl(
    override val name: String,
    override val cardHold: CardHold = CardHold()
) : Player {
    override fun canDraw(): Boolean = cardHold.getTotalPoints() <= Player.THRESHOLD

    override fun compareScore(other: Player): Score {
        if (other !is Dealer) throw IllegalArgumentException("플레이어간에는 점수 비교를 하지 않습니다.")

        return other.compareScore(this).reverse()
    }
}
