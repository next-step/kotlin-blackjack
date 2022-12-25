package blackjack.domain.system

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

/**
 * @see System
 */
class SystemTest : FunSpec({

    context("fun checkBlackJack():") {
        val playerA = Player("A")
        val playerB = Player("B")
        val players = Players(listOf(playerA, playerB))

        val anyCardShape = mockk<CardShape>()
        val cardOfAce = Card(CardNumber.ACE, anyCardShape)
        val cardOfTen = Card(CardNumber.TEN, anyCardShape)

        test("플레이어들 중 블랙잭이 있다면 true를 반환한다.") {
            playerA.hit(cardOfAce)
            playerA.hit(cardOfTen)
            playerB.hit(cardOfTen)
            playerB.hit(cardOfTen)

            val system = System()

            system.checkBlackJack(players) shouldBe true
        }

        test("플레이어들 중 블랙잭이 없다면 false를 반환한다.") {
            playerA.hit(cardOfTen)
            playerA.hit(cardOfTen)
            playerB.hit(cardOfTen)
            playerB.hit(cardOfTen)

            val system = System()

            system.checkBlackJack(players) shouldBe false
        }
    }
})
