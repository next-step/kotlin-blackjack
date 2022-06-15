package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.common.Money
import blackjack.domain.score.CardScore
import blackjack.domain.score.Match

class Player(
    name: String,
    cards: Cards = Cards.empty(),
    playerStatus: PlayerStatus = PlayerStatus.HIT,
    private val batting: Money = Money.ZERO
) : Participant(name, cards) {
    private var _playerStatus: PlayerStatus = playerStatus
    val playerStatus get() = _playerStatus

    override fun isEnd(): Boolean {
        return playerStatus == PlayerStatus.STAY || cards.cardScore() == CardScore.BUST
    }

    fun changeStatus(status: PlayerStatus) {
        check(!isEnd()) {
            "게임이 종료된 이후에는 상태를 변경할 수 없습니다."
        }

        _playerStatus = status
    }

    fun profit(match: Match): Money {
        val cardScore: CardScore = cards.cardScore()
        return when (match) {
            Match.WIN -> batting.multiply(cardScore.profitRate)
            Match.LOSE -> -batting
            Match.DRAW -> Money.ZERO
        }
    }
}
