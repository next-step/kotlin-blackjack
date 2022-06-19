package camp.nextstep.blackjack.player

import camp.nextstep.blackjack.card.Card

class Player(val name: String) {
    private val _cards = mutableListOf<Card>()
    val cards get() = _cards.toList()

    init {
        require(name.isNotBlank()) { "올바른 플레이어 이름을 입력해주세요. ($name)" }
    }

    fun receive(card: Card) {
        _cards.add(card)
    }
}
