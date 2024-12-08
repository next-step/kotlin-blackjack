package blackjack.fixtures

import blackjack.domain.Cards
import blackjack.domain.Player

fun createPlayers(names: List<String> = listOf("홍길동", "홍길덩")): List<Player> {
    return names.map { Player(it, Cards(emptyList())) }
}
