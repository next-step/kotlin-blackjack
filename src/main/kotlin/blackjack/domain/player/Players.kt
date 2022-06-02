package blackjack.domain.player

import blackjack.domain.card.CardDeck

private const val DEFUALT_HIT_COUNT = 2

class Players(
    val players: List<Player>,
) {
    val names: List<Name>
        get() = players.map { it.name }

    val allStay: Boolean
        get() = players.all { it.isStay }

    init {
        require(players.size > 1) { "플레이어는 최소 2명 이상이어야 합니다." }
    }

    fun ready(cardDeck: CardDeck) {
        repeat(DEFUALT_HIT_COUNT) {
            players.forEach { it.hit(cardDeck) }
        }
    }
}
