package blackjack.card

import blackjack.player.Hand
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HandTest {
    private lateinit var cards: Hand

    @Test
    fun sum() {
        cards = Hand(cards = List(size = 3) { CardFixture.generateTestCard(rank = Rank.SIX) })
        cards.sum() shouldBe 18
    }

    @Test
    fun `카드 목록의 ACE가 있으면서 총 합이 21 이하인 경우 ACE는 11을 취한다`() {
        cards =
            Hand(
                cards =
                    listOf(
                        CardFixture.generateTestCard(rank = Rank.ACE),
                        CardFixture.generateTestCard(rank = Rank.TEN),
                    ),
            )
        cards.sum() shouldBe 21
    }

    @Test
    fun `카드 목록의 ACE가 있으면서 총 합이 21을 넘는 경우 ACE는 1을 취한다`() {
        cards =
            Hand(
                cards =
                    listOf(
                        CardFixture.generateTestCard(rank = Rank.ACE),
                        CardFixture.generateTestCard(rank = Rank.ACE),
                        CardFixture.generateTestCard(rank = Rank.ACE),
                    ),
            )
        cards.sum() shouldBe 13
    }
}
