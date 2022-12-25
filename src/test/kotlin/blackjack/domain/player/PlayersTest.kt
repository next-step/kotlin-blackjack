package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import blackjack.domain.card.Deck
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

/**
 * @see Players
 */
class PlayersTest : FunSpec({

    context("fun startGame(): 게임 시작시 카드 2장을 준다.") {
        val gameDeck = Deck
        val playerA = Player("A")
        val playerB = Player("B")
        val players = Players(listOf(playerA, playerB))

        test("모믄 플레이어들은 게임 시작시 카드 두 장을 받는다.") {
            players.startGame(gameDeck)

            players.players.forEach { player ->
                player.getCards().size shouldBe 2
            }
        }
    }

    context("fun checkBlackJack():") {
        val anyCardShape = mockk<CardShape>()
        val cardOfAce = Card(CardNumber.ACE, anyCardShape)
        val cardOfTen = Card(CardNumber.TEN, anyCardShape)
        val blackJackCards = Cards()
        blackJackCards.add(cardOfAce)
        blackJackCards.add(cardOfTen)

        val nonBlackJackCards = Cards()
        nonBlackJackCards.add(cardOfTen)
        nonBlackJackCards.add(cardOfTen)

        test("블랙잭이 있는 경우 모든 플레이어들이 게임을 종료한다.") {
            val playerA = Player("A", blackJackCards)
            val playerB = Player("B", nonBlackJackCards)
            val players = Players(listOf(playerA, playerB))

            players.checkBlackJack()

            playerA.isFinished() shouldBe true
            playerB.isFinished() shouldBe true
        }

        test("블랙잭이 없는 경우 그대로 진행한다.") {
            val playerA = Player("A", nonBlackJackCards)
            val playerB = Player("B", nonBlackJackCards)
            val players = Players(listOf(playerA, playerB))

            players.checkBlackJack()

            playerA.isFinished() shouldBe false
            playerB.isFinished() shouldBe false
        }
    }
})
