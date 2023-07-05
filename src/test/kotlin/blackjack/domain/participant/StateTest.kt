package blackjack.domain.participant

import blackjack.domain.card.TestCards
import blackjack.domain.game.Rank
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class StateTest {

    @Test
    internal fun `플레이어의 점수가 계산된다`() {
        val cards = TestCards.getSixteenPointCards()
        val state = State(cards)

        state.score() shouldBe 16
    }


    @Test
    internal fun `둘 다 블랙잭이면 플레이어는 베팅한 금액을 돌려 받는다`() {
        val blackJack = TestCards.getBlackJack()
        val state = State(blackJack)
        val dealer = Dealer(State(blackJack))

        state.getRank(dealer.state) shouldBe Rank.DRAW
    }

    @Test
    internal fun `처음 두장의 카드 합이 21일 경우 블랙잭이 나온다`() {
        val dealerCard = TestCards.getSixteenPointCards()
        val dealer = Dealer(State(dealerCard))

        val blackJack = TestCards.getBlackJack()
        val state = State(blackJack)

        state.getRank(dealer.state) shouldBe Rank.BLACKJACK
    }

    @Test
    internal fun `딜러가 21을 초과하면 플레이어들은 베팅 금액을 돌려 받는다`() {
        val playerCard = TestCards.getSeventeenPointCards()
        val state = State(playerCard)

        val dealerCard = TestCards.getBurstCards()
        val dealer = Dealer(State(dealerCard))

        state.getRank(dealer.state) shouldBe Rank.WON
    }

    @Test
    internal fun `플레이어가 점수가 더 높으면 플레이어는 이긴다`() {
        val dealerCard = TestCards.getSixteenPointCards()
        val dealer = Dealer(State(dealerCard))

        val playerCard = TestCards.getSeventeenPointCards()
        val state = State(playerCard)

        state.getRank(dealer.state) shouldBe Rank.WON
    }

    @Test
    internal fun `플레이어가 점수가 더 낮으면 플레이어는 진다`() {
        val dealerCard = TestCards.getSeventeenPointCards()
        val dealer = Dealer(State(dealerCard))


        val playerCard = TestCards.getSixteenPointCards()
        val state = State(playerCard)

        state.getRank(dealer.state) shouldBe Rank.LOST
    }
}
