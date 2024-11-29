package blackjack

import blackjack.Suit.SPADE
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

enum class Suit {
    SPADE,
    HEART,
    DIAMOND,
    CLUB,
}

@JvmInline
value class Rank(val rank: String)

data class Card(val rank: Rank, val suit: Suit)

class BlackjackTest : StringSpec({
    "카드는 랭크와 문양으로 이루어진다" {
        val card = Card(Rank("1"), SPADE)
        card.rank shouldBe Rank("1")
        card.suit shouldBe SPADE
    }
})
