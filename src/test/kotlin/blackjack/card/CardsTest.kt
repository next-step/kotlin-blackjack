package blackjack.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardsTest {
    private lateinit var cards: Cards

    @Test
    fun sum() {
        cards = Cards(cards = List(size = 3) { Card(rank = Rank.SIX) })
        cards.sum() shouldBe 18
    }

    @Test
    fun `카드 목록의 ACE가 있으면서 총 합이 21 이하인 경우 ACE는 11을 취한다`() {
        cards =
            Cards(
                cards =
                    listOf(
                        Card(rank = Rank.ACE),
                        Card(rank = Rank.TEN),
                    ),
            )
        cards.sum() shouldBe 21
    }

    @Test
    fun `카드 목록의 ACE가 있으면서 총 합이 21을 넘는 경우 ACE는 1을 취한다`() {
        cards =
            Cards(
                cards =
                    listOf(
                        Card(rank = Rank.ACE),
                        Card(rank = Rank.ACE),
                        Card(rank = Rank.ACE),
                    ),
            )
        cards.sum() shouldBe 13
    }
}
