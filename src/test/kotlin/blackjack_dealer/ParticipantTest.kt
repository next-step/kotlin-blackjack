package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.AllParticipantWithBetAmount
import blackjack_dealer.entity.BlackJackGamer
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.Participants
import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber
import blackjack_dealer.entity.card.CardShape
import blackjack_dealer.entity.state.GamerCurrentState
import blackjack_dealer.entity.toGamerCards
import blackjack_dealer.ui.OutputView
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantTest : StringSpec({
    val cardDeque = CardDeque().create()
    val cards = cardDeque.generateDoubleCard()
    val participant = Participant.newInstance("pita", cards, 10000)

    "생성한 이름이 잘 나온다" {
        val expected = "pita"
        participant.getGamerName() shouldBe expected
    }

    "처음으로 생성한 참가자의 카드 숫자는 2개이다" {
        val expected = 2
        participant.getCurrentCards().trumpCard.count() shouldBe expected
    }

    "처음으로 생성한 참가자의 상태는 HIT 이다" {
        val blackJackCards =
            mutableListOf(Card(CardNumber.A, CardShape.CLOVER), Card(CardNumber.TWO, CardShape.CLOVER))
        val participantWithHit = Participant.newInstance(name = "pita", cards = blackJackCards.toGamerCards())
        val expected = GamerCurrentState.HIT
        participantWithHit.getCurrentGamerState() shouldBe expected
    }

    "처음으로 생성한 참가자의 상태는 운이 좋게도 BLACK_JACK 이다" {
        val blackJackCards =
            mutableListOf(Card(CardNumber.A, CardShape.CLOVER), Card(CardNumber.J, CardShape.CLOVER))
        val participantWithBlackJack = Participant.newInstance(name = "pita", cards = blackJackCards.toGamerCards())
        val expected = GamerCurrentState.INITIAL_BLACKJACK
        participantWithBlackJack.getCurrentGamerState() shouldBe expected
    }

    "처음으로 생성한 참가자의 상태는 canKeepPlayingGame은 true 이다" {
        val blackJackCards =
            mutableListOf(Card(CardNumber.A, CardShape.CLOVER), Card(CardNumber.TWO, CardShape.CLOVER))
        val participantWithHit = Participant.newInstance(name = "pita", cards = blackJackCards.toGamerCards())
        val expected = true
        participantWithHit.canKeepPlayingGame() shouldBe expected
    }

    "draw card 이후 현재 카드 개수 + 1이 된다." {
        participant.drawCard(cardDeque)
        val result = participant.getCurrentCards().trumpCard.count()
        val expected = 3

        result shouldBe expected
    }

    "블랙잭 수행중 n 을 입력하면 현재 state가 stand가 된다." {
        val deque = CardDeque().create()
        val participantCard = cardDeque.generateDoubleCard()
        val dealerCard = cardDeque.generateDoubleCard()
        val allParticipantWithBetAmount = AllParticipantWithBetAmount.newInstance("pita", betAmounts = listOf(1))
        val participants = Participants.newInstance(allParticipantWithBetAmount) { participantCard }
        val dealer = Dealer.newInstance(dealerCard)
        val blackjackGamer = BlackJackGamer(dealer, participants)
        val blackJack = BlackJack(deque, blackjackGamer)
        // 블랙잭 수행
        blackJack.doGame(
            getOneMoreCardInput = { false },
            askGetOneMoreCard = { participant -> OutputView.askGetOneMoreCard(participant) },
            printParticipantInformation = { participant -> OutputView.printParticipantInformation(participant) },
            printGetOneMoreCardForDealer = { OutputView.printGetOneMoreCardForDealer() },
        )
        blackjackGamer.participants.first().getCurrentGamerState() shouldBe GamerCurrentState.STAND
    }

    "플레이어 생성시에 베팅 금액을 정할 수 있다" {
        val expected = 10000
        participant.getCurrentBetAmount() shouldBe expected
    }
})
