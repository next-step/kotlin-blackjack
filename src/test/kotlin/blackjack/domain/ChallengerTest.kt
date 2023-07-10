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
}
