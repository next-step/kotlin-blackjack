package blackjack.business.participants

import blackjack.business.CardFixture.SPACE_ACE
import blackjack.business.CardFixture.SPACE_EIGHT
import blackjack.business.CardFixture.SPACE_TEN
import blackjack.business.util.Money
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("플레이어")
class GamePlayerTest {

    @Test
    fun `플레이어의 카드를 추가한다`() {
        // given
        val gamePlayer = GamePlayer("pobi")

        // when
        gamePlayer.addCard(SPACE_ACE)

        // then
        gamePlayer.cards.size shouldBe 1
        gamePlayer.cards[0] shouldBe SPACE_ACE
    }

    @Test
    fun `플레이어가 추가로 카드를 뽑을 수 있는지 확인한다`() {
        // given
        val gamePlayer = GamePlayer("pobi")
        gamePlayer.addCard(SPACE_ACE)
        gamePlayer.addCard(SPACE_EIGHT)

        // when
        val canDrawCard = gamePlayer.canDrawCard()

        // then
        canDrawCard shouldBe true
    }

    @Test
    fun `플레이어의 스코어를 확인한다`() {
        // given
        val gamePlayer = GamePlayer("pobi")
        gamePlayer.addCard(SPACE_ACE)
        gamePlayer.addCard(SPACE_EIGHT)

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
        val gamePlayer = GamePlayer("pobi")
        gamePlayer.addCard(SPACE_ACE)
        gamePlayer.addCard(SPACE_ACE)
        gamePlayer.addCard(SPACE_TEN)
        gamePlayer.addCard(SPACE_TEN)

        // when
        val actual = gamePlayer.isBust()

        // then
        actual shouldBe true
    }

    @Test
    fun `플레어의 카드의 합이 21을 초과하지 않는지 확인`() {
        // given
        val gamePlayer = GamePlayer("pobi")
        gamePlayer.addCard(SPACE_ACE)
        gamePlayer.addCard(SPACE_TEN)

        // when
        val actual = gamePlayer.isBust()

        // then
        actual shouldBe false
    }

    @Test
    fun `플레이어의 카드의 합이 21미만이면 이동가능 하다`() {
        // given
        val gamePlayer = GamePlayer("pobi")
        gamePlayer.addCard(SPACE_TEN)
        gamePlayer.addCard(SPACE_TEN)

        // when
        val actual = gamePlayer.canDrawCard()

        // then
        actual shouldBe true
    }

    @Test
    fun `플레이어의 카드의 합이 21이상이면 이동 할수 없다`() {
        // given
        val gamePlayer = GamePlayer("pobi")
        gamePlayer.addCard(SPACE_TEN)
        gamePlayer.addCard(SPACE_ACE)

        // when
        val actual = gamePlayer.canDrawCard()

        // then
        actual shouldBe false
    }

    @Test
    fun `베팅 금액을 설정한다`() {
        // given
        val gamePlayer = GamePlayer("pobi")
        val bettingMoney = Money(10000)

        // when
        gamePlayer.setBettingMoney(bettingMoney)

        // then
        gamePlayer.bettingMoney shouldBe bettingMoney
    }

    @Test
    fun `카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃게 된다`() {
        // given
        val gamePlayer = GamePlayer(
            "pobi",
            PlayerCards(SPACE_TEN, SPACE_TEN)
        )
        gamePlayer.setBettingMoney(Money(10000))

        // when
        gamePlayer.addCard(SPACE_TEN)

        // then
        gamePlayer.bettingMoney shouldBe Money(-10000)
    }
}
