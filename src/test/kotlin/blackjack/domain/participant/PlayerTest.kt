package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Character
import blackjack.domain.card.Shape
import blackjack.domain.card.TestCards
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PlayerTest {
    @Test
    internal fun `이름을 입력하면 Player가 생성된다`() {
        Player("pobi").name shouldBe "pobi"
    }

    @Test
    internal fun `빈값을 입력하면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Player("")
        }
    }

    @Test
    internal fun `플레이어의 점수가 계산된다`() {
        val cards = TestCards.getSixteenPointCards()
        val player = Player("pobi", cards)

        player.score() shouldBe 16
    }

    @Test
    internal fun `플레이어는 21점이 넘으면 더이상 게임을 진행할 수 없다`() {
        val cards = TestCards.getBurstCards()
        val player = Player("pobi", cards)

        player.score() shouldBe 22
        player.canProceedTurn() shouldBe false
    }

    @Test
    internal fun `플레이어는 턴을 끝내면 더이상 게임을 진행할 수 없다`() {
        val cards = TestCards.getSixteenPointCards()
        val player = Player("pobi", cards)
        player.finishedTurn()
        player.canProceedTurn() shouldBe false
    }
}
