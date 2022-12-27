package blackjack.domain.player

import blackjack.domain.card.CardVendor
import blackjack.domain.card.Cards
import blackjack.domain.card.cardOf
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PlayerTest : FunSpec({

    test("플레이어는 ${Player.INIT_CARD_COUNT}장의 카드를 지급 받는다.") {
        val cardVendor = CardVendor()

        val player = Player(
            "username",
            cardVendor.drawPlayerFirstTwoCards()
        )

        player shouldNotBe null
    }

    context("플레이어 이름이 빈칸이면, 게임에 참가할 수 없다.") {
        withData(
            nameFn = { "name = [$it], length = ${it.length}" },
            listOf("", " ", "  ", "   ")
        ) { name ->
            val cardVendor = CardVendor()
            assertThrows<IllegalArgumentException> {
                Player(
                    name,
                    cardVendor.drawPlayerFirstTwoCards()
                )
            }
        }
    }

    context("플레이어는 ${Player.INIT_CARD_COUNT}장이 아닌 카드를 지급받을 시, 게임에 참가할 수 없다.") {
        withData(
            nameFn = { "$it 장 카드 지급" },
            (0..Cards.ALL.size).filterNot { it == Player.INIT_CARD_COUNT }
        ) { initCardsCount ->
            val cardVendor = CardVendor()
            val cards = Cards(
                (1..initCardsCount).map { cardVendor.drawCard() }
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
            nameFn = { "$it" },
            (2..20).map { initScore ->
                PlayerDataSet.testDataWithTwoCards(initScore)
            }
        ) { player ->
            val cardVendor = CardVendor()

            player.isFinished() shouldBe false
            assertDoesNotThrow {
                player.hit(cardVendor.drawCard())
            }
        }
    }

    test("21을 초과할 경우, 더이상 게임을 진행하지 않는다.") {
        val player = Player("username", Cards(listOf(cardOf(9), cardOf(9))))

        player.hit(cardOf(4))

        val beforeScore = player.score
        val beforeCardSize = player.cards.size

        player.score shouldBe beforeScore
        player.cards.size shouldBe beforeCardSize
        player.isFinished() shouldBe true
    }

    test("21인 경우, 더이상 hit 하지 않아도 된다.") {
        val player = Player("username", Cards(listOf(cardOf(10), cardOf(11))))

        player.isFinished() shouldBe true
    }
})
