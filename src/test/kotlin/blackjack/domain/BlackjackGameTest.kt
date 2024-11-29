package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.deck.FakeDeckGenerator
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class BlackjackGameTest : StringSpec({
    val names = listOf("a", "b")
    val numbers = List(100) { CardNumber.Ten }
    val shapes = listOf(CardShape.Heart)

    "start()는 모든 플레이어에게 두 장의 카드를 지급한다." {
        val blackjackGame = BlackjackGame(names, FakeDeckGenerator(shapes, numbers))
        blackjackGame.start()
        val players = blackjackGame.getPlayers().players

        players.forAll {
            it.cards.size shouldBe 2
        }
    }

    "dealCardToPlayer()는 플레이어가 카드를 지급할 수 있는 상황이면 카드를 지급하고 참 반환 아니라면 거짓." {
        val blackjackGame = BlackjackGame(names, FakeDeckGenerator(shapes, numbers))
        blackjackGame.dealCardToPlayer("a") shouldBe true
        blackjackGame.dealCardToPlayer("a") shouldBe true
        blackjackGame.dealCardToPlayer("a") shouldBe false
    }
})
