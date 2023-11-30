package blackjack.domain.player

import blackjack.domain.GameResult
import blackjack.domain.card.Deck
import blackjack.domain.rule.DefaultScoringRule
import blackjack.domain.rule.State
import blackjack.domain.rule.TestScoringRule
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ParticipantTest {

    @Test
    fun `Participant는 이름을 갖고 생성한다`() {
        val player = Participant("parker", 1000, DefaultScoringRule())

        player.name shouldBe "parker"
        player.cards.size shouldBe 0
        player.totalScore shouldBe 0
        player.profit shouldBe 0
        player.bet shouldBe 1000
    }

    @Test
    fun `같은 이름을 갖는 Participant는 같은 Participant로 취급한다`() {
        val player1 = Participant("parker", 1000, DefaultScoringRule())
        val player2 = Participant("parker", 1000, DefaultScoringRule())

        player1 shouldBe player2
    }

    @Test
    fun `Participant의 이름은 10자를 넘을 수 없다`() {
        val invalidName = "abvdefghijk"

        invalidName.length shouldBe 11

        shouldThrow<IllegalArgumentException> {
            Participant(invalidName, 1000, DefaultScoringRule())
        }.message shouldBe "이름은 10자를 넘을 수 없습니다."
    }

    @Test
    fun `플레이어는 덱에서 카드를 한 장 뽑을 수 있다`() {
        val player = Participant("parker", 1000, DefaultScoringRule())
        val deck = Deck()

        player.draw(deck)

        player.cards.size shouldBe 1
    }

    @Test
    fun `플레이어는 덱에서 카드를 여러 번 뽑을 수 있다`() {
        val player = Participant("parker", 1000, DefaultScoringRule())
        val deck = Deck()

        player.draw(deck)
        player.draw(deck)
        player.draw(deck)

        player.cards.size shouldBe 3
    }

    @Test
    fun `플레이어는 뽑은 카드가 없으면 총 점수는 0점이다`() {
        val player = Participant("parker", 1000, DefaultScoringRule())

        player.totalScore shouldBe 0
    }

    @Test
    fun `플레이어는 뽑은 카드가 1장 이상이면 총 점수는 0보다 크다`() {
        val player = Participant("parker", 1000, DefaultScoringRule())
        val deck = Deck()

        player.draw(deck)

        player.cards.size shouldBe 1
        player.totalScore shouldBeGreaterThan 0
    }

    @Test
    fun `Participant는 총 점수가 21점을 넘으면 카드를 더이상 받을 수 없다`() {
        val participant = Participant("parker", 1000, DefaultScoringRule())
        val deck = Deck()

        do {
            participant.draw(deck)
        } while (participant.canDraw())

        participant.canDraw() shouldBe false
        participant.totalScore shouldBeGreaterThan DefaultScoringRule.THRESHOLD_SCORE
    }

    @Test
    fun `참가자는 딜러의 결과와 비교해 자신의 결과를 계산한다 - 참가자 승리`() {
        val p1 = Participant("p1", 1000, TestScoringRule(21))
        val dealer = Dealer(TestScoringRule(20))

        p1.compareWith(dealer) shouldBe GameResult.WIN
    }

    @Test
    fun `참가자는 딜러의 결과와 비교해 자신의 결과를 계산한다 - 참가자 패배`() {
        val p1 = Participant("p1", 1000, TestScoringRule(20))
        val dealer = Dealer(TestScoringRule(21))

        p1.compareWith(dealer) shouldBe GameResult.LOSE
    }

    @Test
    fun `참가자는 딜러의 결과와 비교해 자신의 결과를 계산한다 - 참가자 무승부`() {
        val p1 = Participant("p1", 1000, TestScoringRule(20))
        val dealer = Dealer(TestScoringRule(20))

        p1.compareWith(dealer) shouldBe GameResult.TIE
    }

    @Test
    fun `참가자는 게임 시작 후 HIT을 입력하면 카드를 한 장 더 뽑는다`() {
        val participant = Participant("parker", 1000, DefaultScoringRule())
        val deck = Deck()

        participant.beginGame(deck)
        participant.nextTurn(State.HIT, deck)

        participant.cards.size shouldBe 3
    }

    @Test
    fun `참가자는 게임 시작 후, 21이 넘도록 HIT를 하면 BUST 상태로 바뀐다`() {
        val participant = Participant("parker", 1000, DefaultScoringRule())
        val deck = Deck()

        participant.beginGame(deck)
        do {
            participant.nextTurn(State.HIT, deck)
        } while (participant.canDraw())

        participant.state shouldBe State.BUST
        participant.isFinished shouldBe true
    }

    @Test
    fun `참가자는 게임 시작 후 STAY를 입력하면 카드를 한 장 더 뽑는다`() {
        val participant = Participant("parker", 1000, DefaultScoringRule())
        val deck = Deck()

        participant.beginGame(deck)
        participant.nextTurn(State.STAY, deck)

        participant.cards.size shouldBe 2
        participant.isFinished shouldBe true
    }
}
