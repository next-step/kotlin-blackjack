package blackjack.domain.player

import blackjack.domain.card.CardVendor
import blackjack.domain.card.Cards
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldNotBe
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
})
