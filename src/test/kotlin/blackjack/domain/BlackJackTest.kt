package blackjack.domain

import blackjack.util.createPlayers
import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun `블랙잭 게임을 시작할 때 플레이어에게 카드 2장씩 주고 시작해야한다`() {
        val players = createPlayers("pablo", "musk")
        val blackJack = BlackJack(players, CardDeck(defaultDeckGenerator()))

        blackJack.players.players.forEach {
            it.mutableCards.cards.size shouldBeEqual 2
        }
    }
}
