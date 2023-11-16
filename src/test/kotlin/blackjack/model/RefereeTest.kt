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
        Referee.isBlackJack(player) shouldBe false
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
        Referee.isBlackJack(player) shouldBe true
    }
})
