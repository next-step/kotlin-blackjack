package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.CardRank
import blackjack.domain.Challenger
import blackjack.domain.Dealer
import blackjack.domain.SPADES_ACE
import blackjack.domain.SPADES_TEN
import blackjack.domain.SPADES_TWO
import blackjack.domain.createChallengerWithTwelveScore
import blackjack.domain.createDealerWithTwelveScore
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class ChallengerTest {
    @Test
    internal fun `도전자는 카드 점수 합계가 21점 이하면 히트 가능하다`() {
        val sut = Challenger("A")
        sut.initializeHands(SPADES_TWO, SPADES_TWO)
        sut.canHit shouldBe true
        sut.receive(SPADES_TEN)
        sut.canHit shouldBe true
        sut.receive(SPADES_TEN)
        sut.canHit shouldBe false
    }

    @Test
    internal fun `도전자가 스테이(카드를 받지 않음)를 하면 더이상 히트 할 수 없다`() {
        val sut = createChallengerWithTwelveScore()
        sut.canHit shouldBe true
        sut.stay()
        sut.canHit shouldBe false
    }

    @Test
    internal fun `도전자가 먼저 버스트 되면 패배한다`() {
        val sut = createChallengerWithTwelveScore()
        val dealer = createDealerWithTwelveScore()
        sut.receive(SPADES_TEN)

        sut.isWin(dealer) shouldBe false
    }

    @Test
    internal fun `도전자가 버스트되지 않았는데 딜러가 버스트되면 점수에 상관없이 승리한다`() {
        val sut = createChallengerWithTwelveScore()
        val dealer = createDealerWithTwelveScore()
        dealer.receive(SPADES_TEN)

        sut.isWin(dealer) shouldBe true
    }

    @Disabled
    @ParameterizedTest
    @CsvSource(
        "TEN, TWO, TEH, TEN, false",
        "TEN, TEN, TWO, TEN, true",
    )
    internal fun `도전자와 딜러 둘다 버스트되지 않았다면 점수가 큰 쪽이 승리한다`(
        challengerCard1: String,
        challengerCard2: String,
        dealerCard1: String,
        dealerCard2: String,
        challengerWin: Boolean,
    ) {
        val sut = Challenger("A")
        val dealer = Dealer()

        sut.initializeHands(
            Card.of(CardRank.valueOf(challengerCard1)),
            Card.of(CardRank.valueOf(challengerCard2)),
        )
        dealer.initializeHands(
            Card.of(CardRank.valueOf(dealerCard1)),
            Card.of(CardRank.valueOf(dealerCard2)),
        )

        sut.isWin(dealer) shouldBe challengerWin
    }

    @Test
    internal fun `도전자가 블랙잭이되면 배팅 금액의 1,5배를 돌려받는다`() {
        // given : 배팅 금액이 만원인 도전자
        val sut = Challenger(
            name = "A",
            bettingAmount = 10000
        )
        val dealer = createDealerWithTwelveScore()

        // when : 블랙잭이 된 상태에서 금액을 돌려받으면
        sut.initializeHands(SPADES_ACE, SPADES_TEN)
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
        sut.initializeHands(SPADES_ACE, SPADES_TEN)
        dealer.initializeHands(SPADES_ACE, SPADES_TEN)
        val earnings = sut.getEarnings(dealer)

        // then : 수익은 0이된다
        earnings shouldBe 0
    }

    @Disabled
    @Test
    internal fun `도전자가 딜러에게 패배할 경우 베팅 금액을 돌려받지 못한다`() {
        // given : 배팅 금액이 만원인 도전자
        val sut = Challenger(
            name = "A",
            bettingAmount = 10000
        )
        val dealer = Dealer()

        // when : 딜러가 이긴 상태에서 금액을 돌려받으면
        sut.initializeHands(SPADES_TWO, SPADES_TEN)
        dealer.initializeHands(SPADES_ACE, SPADES_TEN)
        val earnings = sut.getEarnings(dealer)

        // then : 배팅 금액을 돌려받지 못하기 때문에 수익은 -10000이 된다
        earnings shouldBe -10000
    }
}
