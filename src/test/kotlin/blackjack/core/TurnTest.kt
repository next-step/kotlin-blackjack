package blackjack.core

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class TurnTest {
    @Test
    fun `Turn class를 STAND 상태를 테스트 한다`() {
        val player = Player(Name("test"))
        val turn = Turn(player, CardDispenser(), { false })
        turn.process()
        player.cards.status shouldBe Status.STAND
    }

    @Test
    fun `Turn class를 BURST 상태를 테스트 한다`() {
        val player = Player(Name("test"))
        var index = 0
        val turn =
            Turn(player, CardDispenser()) {
                if (index++ < Card.CARD_COUNT) true else false
            }
        turn.process()
        player.cards.status shouldBe Status.BUSTED
    }
}
