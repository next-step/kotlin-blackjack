package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.score.CardScore

class Player(
    name: String,
    cards: Cards = Cards.empty(),
    var playerStatus: PlayerStatus = PlayerStatus.HIT
) : Participant(name, cards) {

    override fun isEnd(): Boolean {
        return playerStatus == PlayerStatus.STAY || cards.cardScore() == CardScore.BUST
    }

    fun changeStatus(status: PlayerStatus) {
        check(!isEnd()) {
            "게임이 종료된 이후에는 상태를 변경할 수 없습니다."
        }

        playerStatus = status
    }

    companion object {
        fun ofList(value: String, delimiter: String = ","): List<Player> {
            val names = value.split(delimiter)
            return names.map(::Player)
        }
    }
}
