package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

/**
 * @see Player
 */
class PlayerTest : FunSpec({

    context("카드를 뽑는 경우") {
        val anyCardShape = mockk<CardShape>()
        val cardOfTen = Card(CardNumber.TEN, anyCardShape)

        test("카드를 뽑고 21이 넘지 않는다면 턴이 종료되지 않는다.") {

            val player = Player("test")
            player.hit(cardOfTen)
            player.hit(cardOfTen)

            player.isFinished() shouldBe false
        }

        test("카드를 뽑고 최소 값이 21이 넘는다면 턴이 종료된다.") {
            val player = Player("test")

            player.hit(cardOfTen)
            player.hit(cardOfTen)
            player.hit(cardOfTen)

            player.isFinished() shouldBe true
        }
    }
})
