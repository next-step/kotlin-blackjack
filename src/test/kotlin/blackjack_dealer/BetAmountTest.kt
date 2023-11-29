package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber
import blackjack_dealer.entity.card.CardShape
import blackjack_dealer.entity.state.GamerCurrentState
import blackjack_dealer.entity.state.ParticipantResultState
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BetAmountTest : StringSpec({
    "카드를 더 뽑아 초과 했을 때 베팅 금액을 모두 잃는다" {
        val cards = GamerCards.newInstance(
            listOf(
                Card(cardNumber = CardNumber.J, cardShape = CardShape.CLOVER),
                Card(cardNumber = CardNumber.K, cardShape = CardShape.CLOVER),
                Card(cardNumber = CardNumber.Q, cardShape = CardShape.DIAMOND),
            )
        )
        val participant = Participant.newInstance("석주", cards, 20000)
        val dealerCards = GamerCards.newInstance(
            listOf(
                Card(cardNumber = CardNumber.Q, cardShape = CardShape.CLOVER),
                Card(cardNumber = CardNumber.SEVEN, cardShape = CardShape.DIAMOND),
            )
        )
        val dealer = Dealer.newInstance(dealerCards)
        val expected = -20000

        participant.getCurrentGamerState() shouldBe GamerCurrentState.BUST
        participant.getResultBetAmount(dealer) shouldBe expected
    }

    "승리 했을 때 배팅한 금액 만큼 받는다." {
        val participantCards = GamerCards.newInstance(
            listOf(
                Card(cardNumber = CardNumber.J, cardShape = CardShape.CLOVER),
                Card(cardNumber = CardNumber.K, cardShape = CardShape.CLOVER),
            )
        )
        val dealerCards = GamerCards.newInstance(
            listOf(
                Card(cardNumber = CardNumber.Q, cardShape = CardShape.CLOVER),
                Card(cardNumber = CardNumber.TWO, cardShape = CardShape.DIAMOND),
            )
        )
        val participant = Participant.newInstance("석주", participantCards, 20000)
        val dealer = Dealer.newInstance(dealerCards)
        val participantResult = participant.createParticipantResult(dealer)
        val expected = 20000

        participantResult.resultState shouldBe ParticipantResultState.WIN
        participant.getResultBetAmount(dealer) shouldBe expected
    }

    "처음 두장의 카드 합이 블랙잭인 경우 베팅 금액의 1.5배를 받는다." {
        val cards = GamerCards.newInstance(
            listOf(
                Card(cardNumber = CardNumber.J, cardShape = CardShape.CLOVER),
                Card(cardNumber = CardNumber.A, cardShape = CardShape.CLOVER),
            )
        )
        val participant = Participant.newInstance("석주", cards, 1000)
        val dealerCards = GamerCards.newInstance(
            listOf(
                Card(cardNumber = CardNumber.Q, cardShape = CardShape.CLOVER),
                Card(cardNumber = CardNumber.SEVEN, cardShape = CardShape.DIAMOND),
            )
        )
        val dealer = Dealer.newInstance(dealerCards)
        val expected = 1500

        participant.getCurrentGamerState() shouldBe GamerCurrentState.BLACKJACK
        participant.getResultBetAmount(dealer) shouldBe expected
    }

    "딜러와 플레이어가 모두 블랙잭인 경우 플레이어는 베팅한 금액을 그대로 돌려받는다." {
        val cards = GamerCards.newInstance(
            listOf(
                Card(cardNumber = CardNumber.J, cardShape = CardShape.CLOVER),
                Card(cardNumber = CardNumber.A, cardShape = CardShape.CLOVER),
            )
        )
        val participant = Participant.newInstance("석주", cards, 1000)
        val dealerCards = GamerCards.newInstance(
            listOf(
                Card(cardNumber = CardNumber.A, cardShape = CardShape.HEART),
                Card(cardNumber = CardNumber.Q, cardShape = CardShape.DIAMOND),
            )
        )
        val dealer = Dealer.newInstance(dealerCards)
        val expected = 0

        dealer.getCurrentGamerState() shouldBe GamerCurrentState.BLACKJACK
        participant.getCurrentGamerState() shouldBe GamerCurrentState.BLACKJACK
        participant.getResultBetAmount(dealer) shouldBe expected
    }

    "딜러가 21을 초과한 경우, BUST 아닌 선수는 베팅 금액을 받는다( 되돌려 받는다? or 이겨서 더받는다?) 모르겠음" {

    }
})
