package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSuit
import blackjack.model.state.playState.gameState.Hit
import blackjack.model.state.playState.gameState.Stay
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {

    @Test
    fun `이름을 가지는 플레이어 객체를 생성한다`() {
        // given & when
        val name = "jason"
        val player = Player(name)

        // then
        player.shouldBeInstanceOf<Player>()
        player.name shouldBe name
    }

    @Test
    fun `플레이어의 상태가 hit 이면 카드를 받는다`() {
        // given
        val player = Player("jason")
        val beforeCard = player.cards().size

        // when
        player.draw(Card(CardNumber.ACE, CardSuit.다이아몬드))
        val afterCard = player.cards().size

        // then
        beforeCard shouldBe afterCard - 1
        player.state.shouldBeInstanceOf<Hit>()
    }

    @Test
    fun `플레이어의 상태가 stay 이면 카드를 받지 않는다`() {
        // given
        val player = Player("jason")

        // when
        player.stay()

        // then
        assertThrows<IllegalStateException> {
            player.draw(Card(CardNumber.ACE, CardSuit.다이아몬드))
        }
    }

    @Test
    fun `플레이어의 상태를 stay로 변경한다`() {
        // given
        val player = Player("jason")

        // when
        player.stay()

        // then
        player.state.shouldBeInstanceOf<Stay>()
    }
}
