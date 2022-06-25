package blackjack.domain.player

import blackjack.domain.dealer.Dealer

class Players private constructor(
    val values: List<Player>,
) {
    init {
        require(this.values.isNotEmpty()) { "플레이어가 없으면 게임을 진행할 수 없습니다." }
    }

    fun receiveInitCards(dealer: Dealer) {
        repeat(2) { values.forEach { player -> player.receiveCard(dealer.drawCard()) } }
    }

    companion object {
        fun of(playNameValues: List<String>): Players =
            Players(values = playNameValues.map { Player.from(nameValue = it) })
    }
}
