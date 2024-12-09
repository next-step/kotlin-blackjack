package blackjack.core.turn

import blackjack.core.CardDispenser
import blackjack.core.card.Cards
import blackjack.core.player.Name
import blackjack.core.player.Player
import io.kotest.matchers.ints.shouldNotBeLessThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class TurnTest {
    @Test
    fun `TurnCondition대로 진행됨을 확인한다`() {
        val cardDispenser = CardDispenser()
        val player = Player(Name("Test"))
        val turn = Turn(player, cardDispenser)

        turn.process(
            object : TurnCondition {
                override fun canGo(player: Player): Boolean = false
            },
        )

        player.cards.size shouldBe 0

        turn.process(
            object : TurnCondition {
                override fun canGo(player: Player): Boolean = true
            },
        )

        player.cards.point() shouldNotBeLessThan Cards.MAX_POINT
    }
}
