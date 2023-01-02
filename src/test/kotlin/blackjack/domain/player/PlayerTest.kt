package blackjack.domain.player

import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.data.CardsDataSet
import blackjack.domain.card.data.cardOf
import blackjack.domain.card.vendor.CardVendor
import blackjack.domain.card.vendor.DefaultCardVendor
import blackjack.domain.player.betting.Profit
import blackjack.domain.player.data.PlayerDataSet
import blackjack.view.console.toContentString
import io.kotest.core.Tuple3
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

class PlayerTest : FunSpec({

    test("플레이어는 ${CardHolder.INIT_CARD_COUNT}장의 카드를 지급 받는다.") {
        val givenCardVendor: CardVendor = DefaultCardVendor()

        val player = Player(
            "username",
            givenCardVendor.drawCards()
        )

        player shouldNotBe null
        player.cards shouldHaveSize CardHolder.INIT_CARD_COUNT
    }

    context("플레이어 이름이 빈칸이면, 게임에 참가할 수 없다.") {
        withData(
            nameFn = { "name = [$it], length = ${it.length}" },
            listOf("", " ", "  ", "   ")
        ) { blankName ->
            val givenCardVendor: CardVendor = DefaultCardVendor()

            assertThrows<IllegalArgumentException> {
                Player(
                    blankName,
                    givenCardVendor.drawCards()
                )
            }
        }
    }

    context("플레이어는 ${CardHolder.INIT_CARD_COUNT}장이 아닌 카드를 지급받을 시, 게임에 참가할 수 없다.") {
        withData(
            nameFn = { "$it 장 카드 지급" },
            (0..Cards.ALL.size).filterNot { it == CardHolder.INIT_CARD_COUNT }
        ) { initCardsCount ->
            val givenCardVendor: CardVendor = DefaultCardVendor()

            val cards = Cards(
                (1..initCardsCount).map { givenCardVendor.drawCard() }
            )

            assertThrows<IllegalArgumentException> {
                Player(
                    "username",
                    cards
                )
            }
        }
    }

    context("21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.") {
        withData(
            nameFn = { it.toContentString() },
            (2..20).map { initScore ->
                PlayerDataSet.testDataWithTwoCards(initScore)
            }
        ) { player ->
            val givenCardVendor: CardVendor = DefaultCardVendor()

            player.isNotFinished() shouldBe true
            player.isFinished() shouldBe false

            assertDoesNotThrow {
                player.hit(givenCardVendor.drawCard())
            }
        }
    }

    context("21을 넘지 않을 경우 원한다면, Stay를 외칠수 있다.") {
        withData(
            nameFn = { it.toContentString() },
            (2..20).map { initScore ->
                PlayerDataSet.testDataWithTwoCards(initScore)
            }
        ) { player ->
            player.isNotFinished() shouldBe true
            player.isFinished() shouldBe false

            assertDoesNotThrow {
                player.stay()
            }

            player.isFinished() shouldBe true
        }
    }

    test("21을 초과할 경우, 더이상 게임을 진행하지 않는다.") {
        val player = Player(
            name = "username",
            cards = Cards(listOf(cardOf(9), cardOf(9)))
        )
        val givenCardVendor: CardVendor = DefaultCardVendor()

        player.hit(cardOf(4))

        val beforeScore = player.score
        val beforeCardSize = player.cards.size

        player.score shouldBe beforeScore
        player.cards.size shouldBe beforeCardSize
        player.isNotFinished() shouldBe false
        player.isFinished() shouldBe true

        assertThrows<IllegalStateException> {
            player.hit(givenCardVendor.drawCard())
        }

        assertThrows<IllegalStateException> {
            player.stay()
        }
    }

    test("21인 경우, 더이상 hit 하지 않아도 된다.") {
        val player = Player("username", Cards(listOf(cardOf(10), cardOf(11))))
        val givenCardVendor: CardVendor = DefaultCardVendor()

        player.isNotFinished() shouldBe false
        player.isFinished() shouldBe true

        assertThrows<IllegalStateException> {
            player.hit(givenCardVendor.drawCard())
        }
    }

    test("stay 이미 한번 한 경우, hit 혹은 stay 불가능하다.") {
        val player = Player("username", Cards(listOf(cardOf(10), cardOf(10))))
        val givenCardVendor: CardVendor = DefaultCardVendor()

        player.stay()

        assertThrows<IllegalStateException> {
            player.hit(givenCardVendor.drawCard())
        }

        assertThrows<IllegalStateException> {
            player.stay()
        }
    }

    context("플레이어가 카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃게 된다.") {
        withData(
            nameFn = { "player: ${it.a.toContentString()}, dealerCards: ${it.b.toContentString()}, expectedProfit: ${it.c.amount}" },
            listOf(
                10000.0,
                15000.0,
                20000.0,
                25000.0,
                30000.0,
            ).map { givenBettingAmount ->
                Tuple3(
                    PlayerDataSet.testData("username", givenBettingAmount, cardOf(6), cardOf(10)),
                    CardsDataSet.testData(CardNumber.EIGHT, CardNumber.NINE),
                    Profit(givenBettingAmount * -1.0)
                )
            }
        ) { (player, dealerCards, expectedProfit) ->
            player.hit(cardOf(6))
            player.takeResult(dealerCards)

            player.getProfit() shouldBe expectedProfit
        }
    }

    context("처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.") {
        withData(
            nameFn = { "player: ${it.a.toContentString()}, dealerCards: ${it.b.toContentString()}, expectedProfit: ${it.c.amount}" },
            listOf(
                10000.0,
                15000.0,
                20000.0,
                25000.0,
                30000.0,
            ).map { givenBettingAmount ->
                Tuple3(
                    PlayerDataSet.testData("username", givenBettingAmount, cardOf(11), cardOf(10)),
                    CardsDataSet.testData(CardNumber.EIGHT, CardNumber.NINE),
                    Profit(givenBettingAmount * 1.5)
                )
            }
        ) { (player, dealerCards, expectedProfit) ->
            player.takeResult(dealerCards)

            player.getProfit() shouldBe expectedProfit
        }
    }

    context("딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.") {
        withData(
            nameFn = { "player: ${it.a.toContentString()}, dealerCards: ${it.b.toContentString()}, expectedProfit: ${it.c.amount}" },
            listOf(
                10000.0,
                15000.0,
                20000.0,
                25000.0,
                30000.0,
            ).map { givenBettingAmount ->
                Tuple3(
                    PlayerDataSet.testData("username", givenBettingAmount, cardOf(11), cardOf(10)),
                    CardsDataSet.testData(CardNumber.ACE, CardNumber.KING),
                    Profit(givenBettingAmount * 1.0)
                )
            }
        ) { (player, dealerCards, expectedProfit) ->
            player.takeResult(dealerCards)

            player.getProfit() shouldBe expectedProfit
        }
    }

    context("딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.") {
        withData(
            nameFn = { "player: ${it.a.toContentString()}, dealerCards: ${it.b.toContentString()}, expectedProfit: ${it.c.amount}" },
            listOf(
                10000.0,
                15000.0,
                20000.0,
                25000.0,
                30000.0,
            ).flatMap { givenBettingAmount ->
                (2..20).map { initScore ->
                    Tuple3(
                        Player("username", CardsDataSet.testDataWithTwoCards(initScore), givenBettingAmount),
                        CardsDataSet.testData(CardNumber.TEN, CardNumber.SIX, CardNumber.SIX),
                        Profit(givenBettingAmount * 1.0)
                    )
                }
            }
        ) { (player, dealerCards, expectedProfit) ->
            player.takeResult(dealerCards)

            player.getProfit() shouldBe expectedProfit
        }
    }
})
