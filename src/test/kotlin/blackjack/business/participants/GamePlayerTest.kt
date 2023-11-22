package blackjack.business.participants

import blackjack.business.CardFixture.SPACE_ACE
import blackjack.business.CardFixture.SPACE_EIGHT
import blackjack.business.CardFixture.SPACE_TEN
import blackjack.business.util.Money
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("플레이어")
class GamePlayerTest {

    @Test
    fun `플레이어의 카드를 추가한다`() {
        // given
        val gamePlayer = GamePlayer(
            "pobi",
            PlayerCards(
                listOf(SPACE_ACE)
            ),
            Money(10000)
        )

        // when
        val actualGamePlayer = gamePlayer.addCard(SPACE_EIGHT)

        // then
        actualGamePlayer.cardsCount shouldBe 2
        actualGamePlayer.cards shouldContainExactlyInAnyOrder listOf(SPACE_ACE, SPACE_EIGHT)
        actualGamePlayer.money shouldBe Money(10000)
    }

    @Test
    fun `플레이어는 카드리스틀 추가할 수 있다`() {
        // given
        val gamePlayer = GamePlayer(
            "pobi",
            PlayerCards(
                listOf(SPACE_ACE)
            ),
            Money(10000)
        )

        // when
        val actualGamePlayer = gamePlayer.addCards(listOf(SPACE_EIGHT, SPACE_TEN))

        // then
        actualGamePlayer.cardsCount shouldBe 3
        actualGamePlayer.cards shouldContainExactlyInAnyOrder listOf(SPACE_ACE, SPACE_EIGHT, SPACE_TEN)
        actualGamePlayer.money shouldBe Money(10000)
    }

    @Test
    fun `플레이어가 추가로 카드를 뽑을 수 있는지 확인한다`() {
        // given
        val gamePlayer = GamePlayer(
            "pobi",
            PlayerCards(
                listOf(SPACE_ACE, SPACE_EIGHT)
            )
        )

        // when
        val canDrawCard = gamePlayer.canDrawCard()

        // then
        canDrawCard shouldBe true
    }

    @Test
    fun `플레이어의 스코어를 확인한다`() {
        // given
        val gamePlayer = GamePlayer(
            "pobi",
            PlayerCards(
                listOf(SPACE_ACE, SPACE_EIGHT)
            )
        )

        // when
        val score = gamePlayer.score

        // then
        score shouldBe 19
    }

    @Test
    fun `플레이어 생성시 PlayerCards 주입`() {
        // given
        val playerCards = PlayerCards(listOf(SPACE_ACE, SPACE_EIGHT))

        // when
        val gamePlayer = GamePlayer("pobi", playerCards)

        // then
        gamePlayer.score shouldBe 19
    }

    @Test
    fun `플레이어의 카드의 합이 21을 초과하는지 확인`() {
        // given
        val gamePlayer = GamePlayer(
            "pobi",
            PlayerCards(
                listOf(SPACE_ACE, SPACE_ACE, SPACE_TEN, SPACE_TEN)
            )
        )

        // when
        val actual = gamePlayer.isBust()

        // then
        actual shouldBe true
    }

    @Test
    fun `플레어의 카드의 합이 21을 초과하지 않는지 확인`() {
        // given
        val gamePlayer = GamePlayer(
            "pobi",
            PlayerCards(
                listOf(SPACE_ACE, SPACE_TEN)
            )
        )

        // when
        val actual = gamePlayer.isBust()

        // then
        actual shouldBe false
    }

    @Test
    fun `플레이어의 카드의 합이 21미만이면 이동가능 하다`() {
        // given
        val gamePlayer = GamePlayer(
            "pobi",
            PlayerCards(
                listOf(SPACE_TEN, SPACE_TEN)
            )
        )

        // when
        val actual = gamePlayer.canDrawCard()

        // then
        actual shouldBe true
    }

    @Test
    fun `플레이어의 카드의 합이 21이상이면 이동 할수 없다`() {
        // given
        val gamePlayer = GamePlayer(
            "pobi",
            PlayerCards(
                listOf(SPACE_TEN, SPACE_ACE)
            )
        )

        // when
        val actual = gamePlayer.canDrawCard()

        // then
        actual shouldBe false
    }
}
