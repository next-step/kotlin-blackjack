package blackjack.domain

import blackjack.domain.Card.Companion.ALL_CARDS_SET

data class AllCardMutableSet(val cardSet: MutableSet<Card>) {
    init {
        require(cardSet.containsAll(ALL_CARDS_SET)) { "블랙잭 게임을 하기 위해서는 모든 카드가 준비되어야합니다" }
    }

    
}
