package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.card.data.cardOf
import blackjack.domain.card.vendor.CardVendor
import blackjack.domain.card.vendor.DefaultCardVendor
import blackjack.domain.player.data.PlayerDataSet
import blackjack.view.console.toContentString
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
})
