package blackjack.domain.player

import blackjack.domain.card.CardSet

class Player(val name: String, val cardSet: CardSet) {
    init {
        require(name.isNotBlank()) { "플레이어의 이름은 공백일 수 없습니다." }
    }

    fun receiveCard(cardSet: CardSet): Player {
        return Player(name, this.cardSet + cardSet)
    }
}
