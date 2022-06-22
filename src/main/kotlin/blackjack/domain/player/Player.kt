package blackjack.domain.player

import blackjack.domain.card.Cards

class Player(
    val name: String,
    val cards: Cards = Cards()
) {
    init {
        require(name.isNotBlank()) { "플레이어의 이름은 빈칸 혹은 여백을 허용하지 않습니다. name = $name" }
    }
}
