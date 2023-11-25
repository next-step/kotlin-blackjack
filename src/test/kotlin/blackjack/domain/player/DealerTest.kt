package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.rule.DefaultScoringRule
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `Dealer는 점수 규칙을 가지고 생성한다`() {
        val dealer = Dealer(DefaultScoringRule())

        dealer.cards.size shouldBe 0
        dealer.totalScore shouldBe 0
    }

    @Test
    fun `플레이어는 덱에서 카드를 한 장 뽑을 수 있다`() {
        val player = Dealer(DefaultScoringRule())
        val deck = Deck()

        player.draw(deck)

        player.cards.size shouldBe 1
    }

    @Test
    fun `플레이어는 덱에서 카드를 여러 번 뽑을 수 있다`() {
        val player = Dealer(DefaultScoringRule())
        val deck = Deck()

        player.draw(deck)
        player.draw(deck)
        player.draw(deck)

        player.cards.size shouldBe 3
    }

    @Test
    fun `플레이어는 뽑은 카드가 없으면 총 점수는 0점이다`() {
        val player = Dealer(DefaultScoringRule())

        player.totalScore shouldBe 0
    }

    @Test
    fun `플레이어는 뽑은 카드가 1장 이상이면 총 점수는 0보다 크다`() {
        val player = Dealer(DefaultScoringRule())
        val deck = Deck()

        player.draw(deck)

        player.cards.size shouldBe 1
        player.totalScore shouldBeGreaterThan 0
    }

    @Test
    fun `Dealer는 현재 총 점수가 16점 이하면 자신이 카드를 뽑을 수 있다`() {
        val dealer = Dealer(DefaultScoringRule())
        val deck = Deck()

        dealer.draw(deck)

        dealer.canDraw() shouldBe true
    }

    @Test
    fun `Dealer는 현재 총 점수가 17점 이상이면 자신이 카드를 뽑을 수 없다`() {
        val dealer = Dealer(DefaultScoringRule())
        val deck = Deck()

        while (dealer.totalScore < 16) {
            dealer.draw(deck)
        }

        dealer.canDraw() shouldBe false
    }
}