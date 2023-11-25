package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.rule.DefaultScoringRule
import blackjack.domain.rule.TestScoringRule
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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

    @ParameterizedTest
    @ValueSource(ints = [17, 18, 19, 20, 21, 22, 23, 24, 25])
    fun `Dealer는 참가자와 자신을 비교해서 승패를 결정할 수 있다 - 딜러가 21을 넘으면 무조건 패배`(participantScore: Int) {
        val dealer = Dealer(TestScoringRule(22))
        val participant = Participant("p1", TestScoringRule(participantScore))

        val result = dealer.compareWith(participant)

        result shouldBe GameResult.LOSE
    }

    @ParameterizedTest
    @ValueSource(ints = [22, 23, 24, 25, 26, 27, 28, 29, 30])
    fun `Dealer는 참가자와 자신을 비교해서 승패를 결정할 수 있다 - 딜러가 21을 넘지않고, 참가자가 21을 넘으면 승리`(participantScore: Int) {
        val dealer = Dealer(TestScoringRule(10))
        val participant = Participant("p1", TestScoringRule(participantScore))

        val result = dealer.compareWith(participant)

        result shouldBe GameResult.WIN
    }

    @Test
    fun `Dealer는 참가자와 자신을 비교해서 승패를 결정할 수 있다 - 딜러가 21을 넘지않고 21에 더 가까우면 승리`() {
        val dealer = Dealer(TestScoringRule(20))
        val participant = Participant("p1", TestScoringRule(17))

        val result = dealer.compareWith(participant)

        result shouldBe GameResult.WIN
    }

    @Test
    fun `Dealer는 참가자와 자신을 비교해서 승패를 결정할 수 있다 - 딜러가 21을 넘지않고 21에 더 멀어지면 패배`() {
        val dealer = Dealer(TestScoringRule(20))
        val participant = Participant("p1", TestScoringRule(21))

        val result = dealer.compareWith(participant)

        result shouldBe GameResult.LOSE
    }

    @ParameterizedTest
    @ValueSource(ints = [15, 16, 17, 18, 19, 20, 21])
    fun `Dealer는 참가자와 자신을 비교해서 승패를 결정할 수 있다 - 딜러가 21을 넘지않고 같으면 무승부`(score: Int) {
        val dealer = Dealer(TestScoringRule(score))
        val participant = Participant("p1", TestScoringRule(score))

        val result = dealer.compareWith(participant)

        result shouldBe GameResult.TIE
    }
}