package study.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import study.blackjack.model.BlackjackPlayer
import study.blackjack.model.Card
import study.blackjack.model.CardRank
import study.blackjack.model.Suit

/**
 * @author 이상준
 */
class BlackjackPlayerTest : StringSpec({

    "카드 생성 1개 테스트" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.CLUB, CardRank.THREE))

        player.cards().size() shouldBe 1
    }
    "카드 생성 2개 테스트" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.HEART, CardRank.ACE))
        player.addCard(Card(Suit.DIAMOND, CardRank.EIGHT))

        player.cards().size() shouldBe 2
    }
    "player score 17 test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.DIAMOND, CardRank.SEVEN))
        player.addCard(Card(Suit.DIAMOND, CardRank.TEN))

        player.cards().calculateScore() shouldBe 17
    }
    "player score King test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.DIAMOND, CardRank.SEVEN))
        player.addCard(Card(Suit.DIAMOND, CardRank.KING))

        player.cards().calculateScore() shouldBe 17
    }
    "player score Queen test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.DIAMOND, CardRank.SEVEN))
        player.addCard(Card(Suit.DIAMOND, CardRank.QUEEN))

        player.cards().calculateScore() shouldBe 17
    }
    "player score Jack test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.DIAMOND, CardRank.SEVEN))
        player.addCard(Card(Suit.DIAMOND, CardRank.QUEEN))

        player.cards().calculateScore() shouldBe 17
    }
    "player score 1 ACE test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.HEART, CardRank.ACE))

        player.cards().calculateScore() shouldBe 11
    }
    "player score 21 ACE  test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.DIAMOND, CardRank.ACE))
        player.addCard(Card(Suit.DIAMOND, CardRank.TEN))

        player.cards().calculateScore() shouldBe 21
    }
    "player score 21 이상 ACE 1값 치환  test" {
        val player = BlackjackPlayer("test")
        player.addCard(Card(Suit.DIAMOND, CardRank.ACE))
        player.addCard(Card(Suit.DIAMOND, CardRank.SEVEN))
        player.addCard(Card(Suit.DIAMOND, CardRank.KING))

        player.cards().calculateScore() shouldBe 18
    }
})
