package domain.player

import domain.card.Card
import domain.card.Cards

interface Playable {
    val hands: Cards
    fun receiveCard(card: Card) {
        check(isAvailableReceive()) { "카드를 받을 수 없는 상태입니다." }
        this.hands.add(card)
    }
    fun handsCardScore(): Int = this.hands.score()
    fun isAvailableReceive(): Boolean
}
