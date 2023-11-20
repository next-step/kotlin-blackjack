package blackjack.business.participants

import blackjack.business.CardFixture.SPACE_EIGHT
import blackjack.business.CardFixture.SPACE_FIVE
import blackjack.business.CardFixture.SPACE_FOUR
import blackjack.business.CardFixture.SPACE_NINE
import blackjack.business.CardFixture.SPACE_TEN
import blackjack.business.CardFixture.SPACE_THREE
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
        dealer.getPlayerResult(gamePlayer) shouldBe PlayerResult("pobi", Money(expected))
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
        dealer.getPlayerResult(gamePlayer) shouldBe PlayerResult("pobi", Money(2000))
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
        dealer.getPlayerResult(gamePlayer) shouldBe PlayerResult("pobi", Money(-1000))
    }

    @ParameterizedTest
    @CsvSource(value = ["2000,3000,4000,-2000", "2000,3000,1000,1000", "2000,1000,3000,-1000"])
    fun `플레이어들과 비교하여 dealer의 결과를 반환한다`(pobiMoney: Int, crongMoney: Int, honuxMoney: Int, expected: Int) {
        // given
        val dealer = Dealer(
            PlayerCards(
                SPACE_TEN, SPACE_FOUR
            )
        )
        val players = Players(
            listOf(
                GamePlayer(
                    "pobi",
                    PlayerCards(
                        SPACE_TEN, SPACE_THREE
                    ),
                    Money(pobiMoney)
                ),
                GamePlayer(
                    "crong",
                    PlayerCards(
                        SPACE_TEN, SPACE_FOUR
                    ),
                    Money(crongMoney)
                ),
                GamePlayer(
                    "honux",
                    PlayerCards(
                        SPACE_TEN, SPACE_FIVE
                    ),
                    Money(honuxMoney)
                )
            )
        )

        // when
        val actual = dealer.getDealerResult(players)

        // then
        actual shouldBe Money(expected)
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
