package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CardsTest : BehaviorSpec({

    given("카드 스페이드A 하트9를 받았다면") {
        val cards = Cards(
            Suit.SPADE to Rank.ACE,
            Suit.HEART to Rank.NINE
        )
        `when`("카드 점수를 계산하면") {
            val score = cards.score()
            then("점수는 20점이다.") {
                score shouldBe Score(20)
            }
        }
    }

    given("카드 스페이드A 하트9 다이아몬드 킹을 받았다면") {
        val cards = Cards(
            Suit.SPADE to Rank.ACE,
            Suit.HEART to Rank.NINE,
            Suit.DIAMOND to Rank.KING
        )
        `when`("점수를 계산하면") {
            val score = cards.score()
            then("점수는 20점이다.") {
                score shouldBe Score(20)
            }
        }
    }

    given("카드 3장이 주어졌을 때") {
        val cards = Cards(
            Suit.SPADE to Rank.ACE,
            Suit.HEART to Rank.NINE,
            Suit.DIAMOND to Rank.KING
        )
        `when`("2장의 카드를 받아온다면") {
            val result = cards.take(2)
            then("받아온 카드의 갯수는 2이다.") {
                result.cards.size shouldBe 2
            }
        }
    }
})

fun Cards(vararg cards: Pair<Suit, Rank>): Cards {
    return Cards(cards.map { Card(it.first, it.second) }.toSet())
}
