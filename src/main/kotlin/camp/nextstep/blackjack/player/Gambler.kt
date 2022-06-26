package camp.nextstep.blackjack.player

import camp.nextstep.blackjack.card.Card
import camp.nextstep.blackjack.card.DrawnCard
import camp.nextstep.blackjack.card.Hand

class Gambler(override val name: String) : Player {

    override val hand = Hand()

    override fun receive(card: Card) {
        hand.add(DrawnCard(card).apply { turnUp() })
    }

    init {
        require(name.isNotBlank()) { "올바른 플레이어 이름을 입력해주세요. ($name)" }
    }
}
