package blackjack.domain.player

import blackjack.domain.card.CardDeck
import blackjack.domain.player.vo.Name

private const val DEFAULT_HIT_COUNT = 2
private const val MIN_PLAYER_SIZE = 2

class Players(
    val players: List<Player>,
) {
    val allStay: Boolean
        get() = players.all { it.isStay }

    val names: List<Name>
        get() = players.map { it.name }

    val hittable: List<Player>
        get() = players.filterNot { it.isStay }

    init {
        require(players.size >= MIN_PLAYER_SIZE) { "플레이어는 최소 $MIN_PLAYER_SIZE 명 이상이어야 합니다." }
    }

    fun ready(cardDeck: CardDeck): Unit = repeat(DEFAULT_HIT_COUNT) {
        players.forEach { it.hit(cardDeck) }
    }
}
