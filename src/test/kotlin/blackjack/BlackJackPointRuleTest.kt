package blackjack

import blackjack.domain.BlackJackPointRule
import blackjack.domain.BlackJackStatus.BLACK_JACK
import blackjack.domain.BlackJackStatus.BUST
import blackjack.domain.BlackJackStatus.STAY_OR_HIT
import blackjack.domain.CardType.CLUB
import blackjack.domain.CardType.DIAMOND
import blackjack.domain.CardType.HEART
import blackjack.domain.CardType.SPADE
import blackjack.domain.CardValue.ACE
import blackjack.domain.CardValue.EIGHT
import blackjack.domain.CardValue.FIVE
import blackjack.domain.CardValue.TEN
import blackjack.domain.CardValue.THREE
import blackjack.domain.PlayerCards
import blackjack.domain.PlayingCard.Companion.of
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class BlackJackPointRuleTest : FreeSpec({

    "총 합이 21일 경우 블랙잭이다." {
        val playerCards = PlayerCards()
        playerCards.add(
            THREE of HEART,
            EIGHT of SPADE,
            FIVE of CLUB,
            FIVE of DIAMOND
        )

        BlackJackPointRule.check(playerCards) shouldBe BLACK_JACK
    }

    "총 합이 21 보다 클 경우 버스트 처리된다." {
        val playerCards = PlayerCards()
        playerCards.add(
            EIGHT of HEART,
            EIGHT of SPADE,
            EIGHT of CLUB
        )

        BlackJackPointRule.check(playerCards) shouldBe BUST
    }

    "총 합이 21 보다 작을 경우 stay or hit 처리된다." {
        val playerCards = PlayerCards()
        playerCards.add(EIGHT of HEART, EIGHT of CLUB)

        BlackJackPointRule.check(playerCards) shouldBe STAY_OR_HIT
    }

    "ACE 가 포함된 경우 blackjack > stay or hit > bust 우선 순위로 11 또는 1 로 처리된다." - {
        withData(
            mapOf(
                "블랙잭 with ace by 11" to Pair(arrayOf(TEN of HEART, ACE of HEART), BLACK_JACK),
                "블랙잭 with ace by 1" to Pair(arrayOf(TEN of HEART, ACE of HEART, TEN of CLUB), BLACK_JACK),
                "`stay or hit` with ace by 1" to Pair(arrayOf(FIVE of HEART, ACE of HEART, TEN of CLUB), STAY_OR_HIT),
            )
        ) { (cards, expected) ->
            val playerCards = PlayerCards()
            playerCards.add(*cards)

            BlackJackPointRule.check(playerCards) shouldBe expected
        }
    }
})
