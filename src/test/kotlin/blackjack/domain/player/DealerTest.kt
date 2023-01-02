package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.card.data.cardOf
import blackjack.domain.card.vendor.CardVendor
import blackjack.domain.card.vendor.DefaultCardVendor
import blackjack.domain.player.data.DealerDataSet
import blackjack.domain.player.data.PlayerDataSet
import blackjack.domain.player.result.PlayerResult
import blackjack.view.console.toContentString
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldBeOneOf
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

class DealerTest : FunSpec({
    test("딜러는 ${CardHolder.INIT_CARD_COUNT}장의 카드를 지급 받는다.") {
        val givenCardVendor: CardVendor = DefaultCardVendor()

        val dealer = Dealer(
            "dealer",
            givenCardVendor.drawCards(),
            givenCardVendor
        )

        dealer shouldNotBe null
        dealer.cards shouldHaveSize CardHolder.INIT_CARD_COUNT
    }

    context("딜러의 이름이 빈칸이면, IllegalArgumentException 발생.") {
        withData(
            nameFn = { "name = [$it], length = ${it.length}" },
            listOf("", " ", "  ", "   ")
        ) { blankName ->
            val givenCardVendor: CardVendor = DefaultCardVendor()

            assertThrows<IllegalArgumentException> {
                Dealer(
                    blankName,
                    givenCardVendor.drawCards(),
                    givenCardVendor
                )
            }
        }
    }

    context("Dealer가 Player와 카드를 비교해서 결과를 알려준다.") {
        withData(
            nameFn = { it.cards.toContentString() },
            (2..21).map { initScore ->
                DealerDataSet.testDataWithTwoCards(initScore)
            }
        ) { dealer ->
            val players = (2..21).map { PlayerDataSet.testDataWithTwoCards(it) }

            val playerResults = players.map { givenPlayer ->
                val result = dealer.takeResult(givenPlayer)

                result shouldNotBe null
                result shouldBeOneOf listOf(
                    PlayerResult.WIN,
                    PlayerResult.LOSE,
                    PlayerResult.DRAW
                )
                givenPlayer.finalResult shouldNotBe null
                givenPlayer.finalResult shouldBe result
                result
            }

            playerResults shouldHaveSize players.size
            dealer.getWinCount() shouldBe playerResults.count { it == PlayerResult.LOSE }
            dealer.getLoseCount() shouldBe playerResults.count { it == PlayerResult.WIN }
        }
    }

    context("16을 넘지 않을 경우, 한 장씩 계속 뽑아야 한다.") {
        withData(
            nameFn = { it.cards.toContentString() },
            (2..16).map { initScore ->
                DealerDataSet.testDataWithTwoCards(initScore)
            }
        ) { dealer ->
            assertDoesNotThrow {
                dealer.takeFinalCards()
            }

            dealer.isAddedCards() shouldBe true
        }
    }

    context("16을 넘을 경우, 추가로 카드를 뽑지 않는다.") {
        withData(
            nameFn = { it.cards.toContentString() },
            (17..21).map { initScore ->
                DealerDataSet.testDataWithTwoCards(initScore)
            }
        ) { dealer ->
            assertDoesNotThrow {
                dealer.takeFinalCards()
            }

            dealer.isAddedCards() shouldBe false
        }
    }

    context("21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.") {
        withData(
            nameFn = { it.cards.toContentString() },
            (2..20).map { initScore ->
                DealerDataSet.testDataWithTwoCards(initScore)
            }
        ) { dealer ->

            dealer.isFinished() shouldBe false

            assertDoesNotThrow {
                dealer.hit(dealer.drawCard())
            }
        }
    }

    test("21을 초과할 경우, 더이상 게임을 진행하지 않는다.") {
        val dealer = Dealer(
            name = "dealer",
            cards = Cards.of(cardOf(10), cardOf(6)),
            cardVendor = DefaultCardVendor()
        )

        dealer.hit(cardOf(6))

        val beforeScore = dealer.score
        val beforeCardSize = dealer.cards.size

        dealer.score shouldBe beforeScore
        dealer.cards.size shouldBe beforeCardSize
        dealer.isFinished() shouldBe true

        assertThrows<IllegalStateException> {
            dealer.hit(dealer.drawCard())
        }
    }

    test("21인 경우, 더이상 hit 하지 않아도 된다.") {
        val dealer = DealerDataSet.testDataWithTwoCards(21)

        dealer.isFinished() shouldBe true

        assertThrows<IllegalStateException> {
            dealer.hit(dealer.drawCard())
        }
    }

    test("stay 이미 한번 한 경우, hit 혹은 stay 불가능하다.") {
        val dealer = DealerDataSet.testDataWithTwoCards(20)

        dealer.stay()

        assertThrows<IllegalStateException> {
            dealer.hit(dealer.drawCard())
        }

        assertThrows<IllegalStateException> {
            dealer.stay()
        }
    }
})
