package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "랭크(Rank)와 수트(Suit)를 가진다." {
        forAll(
            row(Rank.ACE, Suit.HEARTS),
            row(Rank.TWO, Suit.DIAMONDS),
            row(Rank.THREE, Suit.CLOVERS),
            row(Rank.FOUR, Suit.SPADES),
            row(Rank.JACK, Suit.HEARTS),
            row(Rank.QUEEN, Suit.DIAMONDS),
            row(Rank.KING, Suit.CLOVERS),
        ) { rank, suit ->
            val card = Card(rank, suit)
            card.rank shouldBe rank
            card.suit shouldBe suit
        }
    }

    "카드가 에이스인지 여부를 반환할 수 있다." {
        val aceCard = Card(Rank.ACE, Suit.HEARTS)
        aceCard.isAce() shouldBe true
    }
})
