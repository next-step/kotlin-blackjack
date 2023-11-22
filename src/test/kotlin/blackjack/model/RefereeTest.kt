package blackjack.model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RefereeTest : StringSpec({

    "ACE+ACE+KING 이 들어온 경우 ACE 를 1로 인식해, 블랙잭이 아니어야만 한다" {
        val player = Player(
            "kim",
            Cards(
                listOf(
                    Card.of(Suit.HEART, CardRank.ACE),
                    Card.of(Suit.DIAMOND, CardRank.ACE),
                    Card.of(Suit.CLOVER, CardRank.KING)
                )
            )
        )
        Referee.isGameOver(player) shouldBe false
    }

    "ACE+KING 이 들어온 경우 ACE 를 11로 인식해, 블랙잭으로 계산 되어야 한다" {
        val player = Player(
            "kim",
            Cards(
                listOf(
                    Card.of(Suit.DIAMOND, CardRank.ACE),
                    Card.of(Suit.CLOVER, CardRank.KING)
                )
            )
        )
        Referee.isGameOver(player) shouldBe true
    }

    "딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해야 한다" {
        val player1 = Player(
            "seoul",
            Cards(
                listOf(
                    Card.of(Suit.DIAMOND, CardRank.ACE),
                    Card.of(Suit.CLOVER, CardRank.KING)
                )
            )
        )
        val player2 = Player(
            "wonju",
            Cards(
                listOf(
                    Card.of(Suit.DIAMOND, CardRank.QUEEN),
                    Card.of(Suit.CLOVER, CardRank.KING)
                )
            )
        )
        val dealer = Dealer(
            Cards(
                listOf(
                    Card.of(Suit.DIAMOND, CardRank.FIVE),
                    Card.of(Suit.CLOVER, CardRank.KING),
                    Card.of(Suit.HEART, CardRank.SEVEN),
                )
            )
        )
        val participants = Participants(setOf(player1, player2), dealer)
        val actual = Referee.blackJackResult(participants)

        actual.dealerResult().point shouldBe 22
        actual.dealerResult().winning shouldBe 0
        actual.dealerResult().losing shouldBe 2

        actual.findPlayerResult(player1).point shouldBe 21
        actual.findPlayerResult(player1).winning shouldBe 1
        actual.findPlayerResult(player1).losing shouldBe 0

        actual.findPlayerResult(player2).point shouldBe 20
        actual.findPlayerResult(player2).winning shouldBe 1
        actual.findPlayerResult(player2).losing shouldBe 0
    }
})
