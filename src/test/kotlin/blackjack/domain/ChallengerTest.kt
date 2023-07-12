package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class ChallengerTest {
    @Test
    internal fun `도전자는 카드 점수 합계가 21점 이하면 히트 가능하다`() {
        val sut = Challenger("A")
        sut.receive(Card.of(CardRank.JACK))
        sut.canHit shouldBe true
        sut.receive(Card.of(CardRank.QUEEN))
        sut.canHit shouldBe true
        sut.receive(Card.of(CardRank.KING))
        sut.canHit shouldBe false
    }

    @Test
    internal fun `도전자가 스테이(카드를 받지 않음)를 하면 더이상 히트 할 수 없다`() {
        val sut = Challenger("A")
        sut.canHit shouldBe true
        sut.stay()
        sut.canHit shouldBe false
    }

    @Test
    internal fun `도전자가 먼저 버스트 되면 패배한다`() {
        val sut = Challenger("A")
        sut.receive(Card.of(CardRank.KING))
        sut.receive(Card.of(CardRank.KING))
        sut.receive(Card.of(CardRank.KING))

        sut.isWin(Dealer()) shouldBe false
    }

    @Test
    internal fun `도전자가 버스트되지 않았는데 딜러가 버스트되면 점수에 상관없이 승리한다`() {
        val sut = Challenger("A")
        val dealer = Dealer()
        dealer.receive(Card.of(CardRank.TWO))
        dealer.receive(Card.of(CardRank.TEN))
        dealer.receive(Card.of(CardRank.TEN))

        sut.isWin(dealer) shouldBe true
    }

    @ParameterizedTest
    @CsvSource(
        "TEN, TWO, true",
        "TWO, TEN, false",
    )
    internal fun `도전자와 딜러 둘다 버스트되지 않았다면 점수가 큰 쪽이 승리한다`(
        challengerCard: String,
        dealerCard: String,
        challengerWin: Boolean,
    ) {
        val sut = Challenger("A")
        val dealer = Dealer()

        sut.receive(Card.of(CardRank.valueOf(challengerCard)))
        dealer.receive(Card.of(CardRank.valueOf(dealerCard)))

        sut.isWin(dealer) shouldBe challengerWin
    }

    @Test
    internal fun `도전자가 블랙잭이되면 배팅 금액의 1,5배를 돌려받는다`() {
        // given : 배팅 금액이 만원인 도전자
        val sut = Challenger(
            name = "A",
            bettingAmount = 10000
        )
        val dealer = Dealer()

        // when : 블랙잭이 된 상태에서 금액을 돌려받으면
        sut.receive(Card.of(CardRank.ACE))
        sut.receive(Card.of(CardRank.TEN))
        val earnings = sut.getEarnings(dealer)

        // then : 배팅 금액의 1.5배를 받는다
        earnings shouldBe 10000 * 1.5
    }

    @Test
    internal fun `도전자와 딜러 모두 블랙잭이되면 도전자는 베팅한 금액을 그대로 돌려받는다`() {
        // given : 배팅 금액이 만원인 도전자
        val sut = Challenger(
            name = "A",
            bettingAmount = 10000
        )
        val dealer = Dealer()

        // when : 도전자와 딜러 모두 블랙잭이 된 상태에서는 베팅 금액을 그대로 돌려받기 때문에
        sut.receive(Card.of(CardRank.ACE))
        sut.receive(Card.of(CardRank.TEN))
        dealer.receive(Card.of(CardRank.ACE))
        dealer.receive(Card.of(CardRank.TEN))
        val earnings = sut.getEarnings(dealer)

        // then : 수익은 0이된다
        earnings shouldBe 0
    }

    @Test
    internal fun `도전자가 딜러에게 패배할 경우 베팅 금액을 돌려받지 못한다`() {
        // given : 배팅 금액이 만원인 도전자
        val sut = Challenger(
            name = "A",
            bettingAmount = 10000
        )
        val dealer = Dealer()

        // when : 딜러가 이긴 상태에서 금액을 돌려받으면
        sut.receive(Card.of(CardRank.TWO))
        sut.receive(Card.of(CardRank.TEN))
        dealer.receive(Card.of(CardRank.ACE))
        dealer.receive(Card.of(CardRank.TEN))
        val earnings = sut.getEarnings(dealer)

        // then : 배팅 금액을 돌려받지 못하기 때문에 수익은 -10000이 된다
        earnings shouldBe -10000
    }
}
