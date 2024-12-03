package study.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import study.blackjack.model.BlackjackPlayer
import study.blackjack.model.Card
import study.blackjack.model.Suit

/**
 * @author 이상준
 */
class BlackjackPlayerTest : StringSpec({

    "카드 생성 1개 테스트" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.CLUB, 3))

        player.cards.size shouldBe 1
    }
    "카드 생성 2개 테스트" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.HEART, 4))
        player.addCard(Card(Suit.DIAMOND, 5))

        player.cards.size shouldBe 2
    }
    "player score 17 test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.DIAMOND, 7))
        player.addCard(Card(Suit.DIAMOND, 10))

        player.calculateScore() shouldBe 17
    }
    "player score King test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.DIAMOND, 7))
        player.addCard(Card(Suit.DIAMOND, 13))

        player.calculateScore() shouldBe 17
    }
    "player score Queen test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.DIAMOND, 7))
        player.addCard(Card(Suit.DIAMOND, 12))

        player.calculateScore() shouldBe 17
    }
    "player score Jack test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.DIAMOND, 7))
        player.addCard(Card(Suit.DIAMOND, 11))

        player.calculateScore() shouldBe 17
    }
    "player score 1 ACE test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.HEART, 1))

        player.calculateScore() shouldBe 11
    }
    "player score 21 ACE  test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.DIAMOND, 1))
        player.addCard(Card(Suit.DIAMOND, 11))

        player.calculateScore() shouldBe 21
    }
    "player score 21 이상 ACE 1값 치환  test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.DIAMOND, 1))
        player.addCard(Card(Suit.DIAMOND, 7))
        player.addCard(Card(Suit.DIAMOND, 10))

        player.calculateScore() shouldBe 18
    }
})

