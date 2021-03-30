package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player
import blackjack.domain.winning.GameResult

abstract class Finished : State {
    override val isFinished: Boolean = true
    override fun draw(card: Card): State {
        throw IllegalStateException("더 이상 카드를 받을 수 없습니다.")
    }

    abstract fun isWinning(player: Player, dealer: Dealer): GameResult
}
