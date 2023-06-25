package blackjack

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    internal fun `카드는 랭크와 무늬 그리고 포인트를 가진다`() {
        val sut = Card(
            rank = CardRank.ACE,
            suit = CardSuit.HEART,
        )
        sut.rank shouldBe CardRank.ACE
        sut.suit shouldBe CardSuit.HEART
        sut.points shouldContainExactly listOf(1, 11)
    }

    @Test
    internal fun `카드는 랭크와 무늬를 조합해 총 52장이 있다`() {
        Card.CARD_SET shouldHaveSize 52
    }
}
