package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "카드를 카드를 추가로 뽑을 수 있는 상황인지 확인할 수 있다." {
        forAll(
            row((listOf(Card(Rank.ACE, Suit.SPADES), Card(Rank.TWO, Suit.HEARTS))), true),
            row((listOf(Card(Rank.TEN, Suit.DIAMONDS), Card(Rank.SIX, Suit.CLOVERS))), true),
            row((listOf(Card(Rank.TEN, Suit.DIAMONDS), Card(Rank.SEVEN, Suit.CLOVERS))), false),
        ) { cards, expected ->
            val dealer = Dealer.createNew(cards)
            dealer.isDrawable() shouldBe expected
        }
    }
})
