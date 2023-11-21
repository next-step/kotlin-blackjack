package blackjack.model

import blackjack.model.pack.ShuffledPack
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "딜러는 처음에 받은 2장의 카드 점수 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 한다" {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card.of(Suit.CLOVER, CardRank.KING),
                    Card.of(Suit.HEART, CardRank.SIX),
                )
            )
        )
        dealer.play(ShuffledPack)
        dealer.countOfCards() shouldBe 3
    }

    "딜러는 카드의 점수 합계가 17점 이상이면 추가로 받을 수 없다" {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card.of(Suit.CLOVER, CardRank.KING),
                    Card.of(Suit.HEART, CardRank.SEVEN),
                )
            )
        )
        dealer.play(ShuffledPack)
        dealer.countOfCards() shouldBe 2
    }
})
