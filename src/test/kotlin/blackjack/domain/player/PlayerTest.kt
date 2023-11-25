package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.rule.DefaultScoringRule
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `Player는 이름을 갖고 생성한다`() {
        val player = Player("parker", DefaultScoringRule())

        player.name shouldBe "parker"
        player.cards.size shouldBe 0
        player.totalScore shouldBe 0
    }

    @Test
    fun `같은 이름을 갖는 Player는 같은 Player로 취급한다`() {
        val player1 = Player("parker", DefaultScoringRule())
        val player2 = Player("parker", DefaultScoringRule())

        player1 shouldBe player2
    }

    @Test
    fun `Player의 이름은 10자를 넘을 수 없다`() {
        val invalidName = "abvdefghijk"

        invalidName.length shouldBe 11

        shouldThrow<IllegalArgumentException> {
            Player(invalidName, DefaultScoringRule())
        }.message shouldBe "이름은 10자를 넘을 수 없습니다."
    }

    @Test
    fun `플레이어는 덱에서 카드를 한 장 뽑을 수 있다`() {
        val deck = Deck()
        val player = Player("parker", DefaultScoringRule())

        player.draw(deck)

        player.cards.size shouldBe 1
    }

    @Test
    fun `플레이어는 덱에서 카드를 여러 번 뽑을 수 있다`() {
        val deck = Deck()
        val player = Player("parker", DefaultScoringRule())

        player.draw(deck)
        player.draw(deck)
        player.draw(deck)

        player.cards.size shouldBe 3
    }

    @Test
    fun `플레이어는 뽑은 카드가 없으면 총 점수는 0점이다`() {
        val player = Player("parker", DefaultScoringRule())

        player.totalScore shouldBe 0
    }

    @Test
    fun `플레이어는 뽑은 카드가 1장 이상이면 총 점수는 0보다 크다`() {
        val deck = Deck()
        val player = Player("parker", DefaultScoringRule())

        player.draw(deck)

        player.cards.size shouldBe 1
        player.totalScore shouldBeGreaterThan 0
    }

    @Test
    fun `총 점수가 21점을 넘으면 카드를 더이상 받을 수 없다`() {
        val deck = Deck()
        val player = Player("parker", DefaultScoringRule())

        do {
            player.draw(deck)
        } while (player.canDraw())

        player.canDraw() shouldBe false
        player.totalScore shouldBeGreaterThan DefaultScoringRule.THRESHOLD_SCORE
    }
}
