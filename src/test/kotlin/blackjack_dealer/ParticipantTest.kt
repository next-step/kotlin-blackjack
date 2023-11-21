package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.Participants
import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber
import blackjack_dealer.entity.card.CardShape
import blackjack_dealer.entity.state.GamerCurrentState
import blackjack_dealer.entity.toGamerCards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantTest : StringSpec({
    val cardDeque = CardDeque.create()
    val cards = listOf(cardDeque.cardDeque.removeLast(), cardDeque.cardDeque.removeLast())
    val participant = Participant.newInstance("pita", cards.toGamerCards())

    "생성한 이름이 잘 나온다" {
        val expected = "pita"
        participant.getGamerName() shouldBe expected
    }

    "처음으로 생성한 참가자의 카드 숫자는 2개이다" {
        val expected = 2
        participant.getCurrentCards().count() shouldBe expected
    }

    "처음으로 생성한 참가자의 상태는 HIT 이다" {
        val blackJackCards =
            GamerCards(listOf(Card(CardNumber.A, CardShape.CLOVER), Card(CardNumber.TWO, CardShape.CLOVER)))
        val participantWithHit = Participant.newInstance(name = "pita", cards = blackJackCards)
        val expected = GamerCurrentState.HIT
        participantWithHit.getCurrentGamerState() shouldBe expected
    }

    "처음으로 생성한 참가자의 상태는 운이 좋게도 BLACK_JACK 이다" {
        val blackJackCards =
            GamerCards(listOf(Card(CardNumber.A, CardShape.CLOVER), Card(CardNumber.J, CardShape.CLOVER)))
        val participantWithBlackJack = Participant.newInstance(name = "pita", cards = blackJackCards)
        val expected = GamerCurrentState.BLACKJACK
        participantWithBlackJack.getCurrentGamerState() shouldBe expected
    }

    "처음으로 생성한 참가자의 상태는 canJoinGame은 true 이다" {
        val blackJackCards =
            GamerCards(listOf(Card(CardNumber.A, CardShape.CLOVER), Card(CardNumber.TWO, CardShape.CLOVER)))
        val participantWithHit = Participant.newInstance(name = "pita", cards = blackJackCards)
        val expected = true
        participantWithHit.canJoinGame() shouldBe expected
    }

    "draw card 이후 현재 카드 개수 + 1이 된다." {
        participant.drawCard(cardDeque)
        val result = participant.getCurrentCards().count()
        val expected = 3

        result shouldBe expected
    }

    "블랙잭 수행중 n 을 입력하면 현재 state가 stand가 된다." {
        val deque = CardDeque.create()
        val participantCard = listOf(deque.cardDeque.removeLast(), deque.cardDeque.removeLast()).toGamerCards()
        val dealerCard = listOf(deque.cardDeque.removeLast(), deque.cardDeque.removeLast()).toGamerCards()
        val pita = Participant.newInstance("pita", participantCard)
        val dealer = Dealer.newInstance(dealerCard)
        val blackJack = BlackJack(deque, dealer, Participants(listOf(pita)))
        // 블랙잭 수행
        blackJack.doGame {
            false
        }
        pita.getCurrentGamerState() shouldBe GamerCurrentState.STAND
    }
})
