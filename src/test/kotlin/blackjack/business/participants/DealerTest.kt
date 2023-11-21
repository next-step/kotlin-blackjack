package blackjack.business.participants

import blackjack.business.CardFixture.SPACE_ACE
import blackjack.business.CardFixture.SPACE_EIGHT
import blackjack.business.CardFixture.SPACE_FIVE
import blackjack.business.CardFixture.SPACE_NINE
import blackjack.business.CardFixture.SPACE_TEN
import blackjack.business.card.Card
import blackjack.business.card.CardDesk
import blackjack.business.card.Rank
import blackjack.business.card.Suit
import blackjack.business.util.Money
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DealerTest {
    @Test
    fun `딜러의 카드를 한장씩 추가 한다`() {
        // given
        val dealer = Dealer()
        val cardDesk = CardDesk()
        val card = cardDesk.draw()

        // when
        dealer.addCard(card)

        // then
        dealer.cards.size shouldBe 1
        dealer.cards shouldContainExactlyInAnyOrder listOf(card)
    }

    @ParameterizedTest
    @CsvSource(value = ["EIGHT,2000", "NINE,0", "TEN,-1000"])
    fun `딜러의 카드와 플레이어의 카드를 비교한다`(target: Rank, expected: Int) {
        // given
        val dealer = Dealer(
            PlayerCards(
                SPACE_FIVE, Card.of(Suit.CLUB, target)
            )
        )
        val gamePlayer = GamePlayer(
            "pobi",
            PlayerCards(
                SPACE_FIVE, SPACE_NINE
            ),
            Money(1000)
        )

        // when,then
        dealer.getPlayerResult(gamePlayer) shouldBe PlayerResult("pobi", expected)
    }

    @Test
    fun `플레이어의 카드가 21이고 2장이며 딜러의 카드가 21이 아니면 플레이어는 1점5의 돈을 얻는다`() {
        // given
        val dealer = Dealer(
            PlayerCards(
                SPACE_FIVE, SPACE_FIVE
            )
        )
        val gamePlayer = GamePlayer(
            "pobi",
            PlayerCards(
                SPACE_TEN, SPACE_ACE
            ),
            Money(1000)
        )

        // when,then
        dealer.getPlayerResult(gamePlayer) shouldBe PlayerResult("pobi", 2500)
    }

    @Test
    fun `딜러가 bust면 플레이어는 무조건 이긴다`() {
        // given
        val dealer = Dealer(
            PlayerCards(
                SPACE_TEN, SPACE_FIVE, SPACE_TEN
            )
        )
        val gamePlayer = GamePlayer(
            "pobi",
            PlayerCards(
                SPACE_TEN, SPACE_TEN
            ),
            Money(1000)
        )

        // when,then
        dealer.getPlayerResult(gamePlayer) shouldBe PlayerResult("pobi", 2000)
    }

    @Test
    fun `딜러가 bust아닐 때 플레이어가 bust면 플레이어는 무조건 진다`() {
        // given
        val dealer = Dealer(
            PlayerCards(
                SPACE_FIVE, SPACE_TEN, SPACE_FIVE
            )
        )
        val gamePlayer = GamePlayer(
            "pobi",
            PlayerCards(
                SPACE_TEN, SPACE_FIVE, SPACE_TEN
            ),
            Money(1000)
        )

        // when,then
        dealer.getPlayerResult(gamePlayer) shouldBe PlayerResult("pobi", -1000)
    }

    @Test
    fun `딜러의 카드가 16이하이면 카드를 한장 더 받는다`() {
        // given
        val dealer = Dealer(
            PlayerCards(
                SPACE_TEN, SPACE_FIVE
            )
        )
        val cardDesk = CardDesk()

        // when
        dealer.executeCardDraws(cardDesk) {}

        // then
        dealer.cards.size shouldBe 3
    }

    @Test
    fun `딜러의 카드가 17이상이면 카드를 받지 않는다`() {
        // given
        val dealer = Dealer(
            PlayerCards(
                SPACE_TEN, SPACE_EIGHT
            )
        )
        val cardDesk = CardDesk()

        // when
        dealer.executeCardDraws(cardDesk) {}

        // then
        dealer.cards.size shouldBe 2
    }
}
