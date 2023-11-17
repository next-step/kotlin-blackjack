package blackjack.business

import blackjack.business.CardFixture.SPACE_ACE
import blackjack.business.CardFixture.SPACE_EIGHT
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("플레이어")
class PlayerTest {

    @Test
    fun `플레이어의 카드를 추가한다`() {
        // given
        val player = Player.from("pobi")

        // when
        player.addCard(SPACE_ACE)

        // then
        player.cards.size shouldBe 1
        player.cards[0] shouldBe Card(Suit.SPADE, Rank.ACE)
    }

    @Test
    fun `플레이어가 추가로 카드를 뽑을 수 있는지 확인한다`() {
        // given
        val player = Player.from("pobi")
        player.addCard(SPACE_ACE)
        player.addCard(SPACE_EIGHT)

        // when
        val canDrawCard = player.canDrawCard()

        // then
        canDrawCard shouldBe true
    }

    @Test
    fun `플레이어의 스코어를 확인한다`() {
        // given
        val player = Player.from("pobi")
        player.addCard(SPACE_ACE)
        player.addCard(SPACE_EIGHT)

        // when
        val score = player.score

        // then
        score shouldBe 19
    }

    @Test
    fun `플레이어 생성시 PlayerCards 주입`() {
        // given
        val playerCards = PlayerCards(listOf(SPACE_ACE, SPACE_EIGHT))

        // when
        val player = Player.from("pobi", playerCards)

        // then
        player.score shouldBe 19
    }
}
