package blackjack.business

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
        player.addCard(Card(Suit.SPADE, Rank.ACE))

        // then
        player.cards.size shouldBe 1
        player.cards[0] shouldBe Card(Suit.SPADE, Rank.ACE)
    }

    @Test
    fun `플레이어가 추가로 카드를 뽑을 수 있는지 확인한다`() {
        // given
        val player = Player.from("pobi")
        player.addCard(Card(Suit.SPADE, Rank.ACE))
        player.addCard(Card(Suit.SPADE, Rank.EIGHT))

        // when
        val canDrawCard = player.canDrawCard()

        // then
        canDrawCard shouldBe true
    }

    @Test
    fun `플레이어의 스코어를 확인한다`() {
        // given
        val player = Player.from("pobi")
        player.addCard(Card(Suit.SPADE, Rank.ACE))
        player.addCard(Card(Suit.SPADE, Rank.EIGHT))

        // when
        val score = player.score

        // then
        score shouldBe 19
    }

    @Test
    fun `플레이어 생성시 PlayerCards 주입`() {
        // given
        val playerCards = PlayerCards(listOf(Card(Suit.SPADE, Rank.ACE), Card(Suit.SPADE, Rank.EIGHT)))

        // when
        val player = Player.from("pobi", playerCards)

        // then
        player.score shouldBe 19
    }
}
