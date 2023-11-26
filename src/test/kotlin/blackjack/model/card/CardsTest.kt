package blackjack.model.card

import blackjack.model.BlackJackStatus
import blackjack.model.playable.BlackjackScore
import blackjack.model.playable.impl.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({

    "Cards 의 모든 카드의 점수의 합을 반환해야 한다" {
        val actual = Cards(
            listOf(
                Card.of(Suit.CLOVER, CardRank.THREE),
                Card.of(Suit.DIAMOND, CardRank.FIVE),
                Card.of(Suit.HEART, CardRank.EIGHT),
                Card.of(Suit.HEART, CardRank.KING)
            )
        ).totalScore()
        actual shouldBe BlackjackScore(3 + 5 + 8 + 10)
    }

    "합계점수가 21를 초과 하는 경우, ACE 가 1로 인식되어야 한다" {
        val actual = Cards(
            listOf(
                Card.of(Suit.HEART, CardRank.ACE),
                Card.of(Suit.DIAMOND, CardRank.FIVE),
                Card.of(Suit.HEART, CardRank.EIGHT),
                Card.of(Suit.HEART, CardRank.KING)
            )
        ).totalScore()
        actual shouldBe BlackjackScore(1 + 5 + 8 + 10)
    }

    "합계점수가 21를 이하인 경우, ACE 가 11로 인식되어야 한다" {
        val actual = Cards(
            listOf(
                Card.of(Suit.HEART, CardRank.ACE),
                Card.of(Suit.HEART, CardRank.THREE),
                Card.of(Suit.DIAMOND, CardRank.FIVE),
            )
        ).totalScore()
        actual shouldBe BlackjackScore(11 + 3 + 5)
    }

    "ACE+ACE+KING 이 들어온 경우 ACE 를 1로 인식해, 스코어가 12 이어야 만 한다" {
        val player = Player(
            "kim",
            CardFixture.makeCards(CardFixture.ace1, CardFixture.ace2, CardFixture.king)
        )
        player.status() shouldBe BlackJackStatus.ALIVE
        player.isBurst() shouldBe false
        player.score() shouldBe BlackjackScore(12)
    }

    "ACE+KING 이 들어온 경우 ACE 를 11로 인식해, 블랙잭 점수인 21 로 계산 되어야 한다" {
        val player = Player(
            "kong",
            CardFixture.makeCards(CardFixture.ace1, CardFixture.king)
        )
        player.status() shouldBe BlackJackStatus.ALIVE
        player.isBurst() shouldBe false
        player.score() shouldBe BlackjackScore(21)
    }

    "카드가 여러장 들어있는 상황에서도 합계 점수를 정확히 구해낼 수 있어야한다" {
        val player = Player(
            "kong",
            CardFixture.makeCards(
                CardFixture.ace1,
                CardFixture.two,
                CardFixture.three,
                CardFixture.four,
                CardFixture.five,
                CardFixture.six,
                CardFixture.seven,
                CardFixture.eight,
                CardFixture.nine,
                CardFixture.ten,
                CardFixture.king,
                CardFixture.jack,
                CardFixture.queen
            )
        )
        player.score() shouldBeIn listOf(BlackjackScore(85), BlackjackScore(95))
    }
})
