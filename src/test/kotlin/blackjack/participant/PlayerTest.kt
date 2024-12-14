package blackjack.participant

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardSuit
import blackjack.domain.PlayerState
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "플레이어는 카드를 한 장 받을 수 있다." {
        // Arrange:
        val player = Player(PlayerName("test"))
        val card = Card(CardNumber.ACE, CardSuit.SPADES)

        // Act:
        player.take(listOf(card))

        // Assert:
        player.cards.cards.size shouldBe 1
    }

    "플레이어는 카드를 여러 장 받을 수 있다." {
        // Arrange:
        val player = Player(PlayerName("test"))
        val card1 = Card(CardNumber.ACE, CardSuit.SPADES)
        val card2 = Card(CardNumber.TWO, CardSuit.SPADES)

        // Act:
        player.take(listOf(card1, card2))

        // Assert:
        player.cards.cards.size shouldBe 2
    }

    "플레이어는 버스트가 되었는지 확인할 수 있다." {
        // Arrange:
        val player = Player(PlayerName("test"))
        val card1 = Card(CardNumber.TEN, CardSuit.SPADES)
        val card2 = Card(CardNumber.TEN, CardSuit.HEARTS)
        val card3 = Card(CardNumber.TWO, CardSuit.SPADES)

        // Act:
        player.take(listOf(card1, card2, card3))

        // Assert:
        player.isBust() shouldBe true
    }

    "플레이어의 점수를 확인할 수 있다." {
        // Arrange:
        val player = Player(PlayerName("test"))
        val card1 = Card(CardNumber.TEN, CardSuit.SPADES)
        val card2 = Card(CardNumber.TEN, CardSuit.SPADES)
        val card3 = Card(CardNumber.TWO, CardSuit.SPADES)

        // Act:
        player.take(listOf(card1, card2, card3))

        // Assert:
        player.bestScore() shouldBe 22
    }

    "플레이어의 점수를 표기한다." {
        // Arrange:
        val player = Player(PlayerName("test"))
        val card1 = Card(CardNumber.TEN, CardSuit.SPADES)
        val card2 = Card(CardNumber.TEN, CardSuit.SPADES)
        val card3 = Card(CardNumber.TWO, CardSuit.SPADES)

        // Act:
        player.take(listOf(card1, card2, card3))

        // Assert:
        player.toString() shouldBe "test카드: 10스페이드, 10스페이드, 2스페이드"
    }

    "플레이어의 점수가 21점이면 블랙잭이다." {
        // Arrange:
        val player = Player(PlayerName("test"))
        val card1 = Card(CardNumber.TEN, CardSuit.SPADES)
        val card2 = Card(CardNumber.NINE, CardSuit.HEARTS)
        val card3 = Card(CardNumber.TWO, CardSuit.HEARTS)
        player.take(listOf(card1, card2, card3))

        // Act:
        player.refreshState()

        // Assert:
        player.state shouldBe PlayerState.BLACKJACK
    }

    "플레이어의 점수가 21점이 넘으면 버스트다." {
        // Arrange:
        val player = Player(PlayerName("test"))
        val card1 = Card(CardNumber.TEN, CardSuit.SPADES)
        val card2 = Card(CardNumber.TEN, CardSuit.HEARTS)
        val card3 = Card(CardNumber.TWO, CardSuit.SPADES)
        player.take(listOf(card1, card2, card3))

        // Act:
        player.refreshState()

        // Assert:
        player.state shouldBe PlayerState.BUST
    }
})
