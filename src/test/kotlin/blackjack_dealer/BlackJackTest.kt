package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.entity.BlackJackGamer
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.Participants
import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber
import blackjack_dealer.entity.card.CardShape
import blackjack_dealer.entity.toGamerCards
import blackjack_dealer.ui.OutputView
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackTest : StringSpec({
    val cardDeque = CardDeque().create()
    val cards = cardDeque.generateDoubleCard()
    "블랙잭을 수행하여 한장 더 받기를 선택시에 카드의 숫자가 한장 증가한다" {
        val customCards =
            mutableListOf(Card(CardNumber.J, CardShape.HEART), Card(CardNumber.J, CardShape.CLOVER)).toGamerCards()
        val dealerCards =
            mutableListOf(Card(CardNumber.J, CardShape.SPADE), Card(CardNumber.THREE, CardShape.CLOVER)).toGamerCards()

        val participant = Participants.newInstance("pita") { customCards }
        val dealer = Dealer.newInstance(dealerCards)
        val blackjackGamer = BlackJackGamer(dealer, participant)
        val expected = 3
        val blackJack = BlackJack(cardDeque, blackjackGamer)
        // 블랙잭 수행
        blackJack.doGame(
            getOneMoreCardInput = { true },
            askGetOneMoreCard = { participant -> OutputView.askGetOneMoreCard(participant) },
            printParticipantInformation = { participant -> OutputView.printParticipantInformation(participant) },
            printGetOneMoreCardForDealer = { OutputView.printGetOneMoreCardForDealer() },
        )
        // 한장만 더 받기
        blackjackGamer.participants.first().getCurrentCards().trumpCard.count() shouldBe expected
    }

    "블랙잭을 수행하여 한장 더 안받기 선택시에 카드의 숫자가 개수가 동일하다" {
        val participants = Participants.newInstance("pita") { cards }
        val dealerCards = cardDeque.generateDoubleCard()
        val dealer = Dealer.newInstance(dealerCards)
        val blackjackGamer = BlackJackGamer(dealer, participants)
        val expected = 2
        val blackJack = BlackJack(cardDeque, blackjackGamer)
        // 블랙잭 수행
        blackJack.doGame(
            getOneMoreCardInput = { false },
            askGetOneMoreCard = { participant -> OutputView.askGetOneMoreCard(participant) },
            printParticipantInformation = { participant -> OutputView.printParticipantInformation(participant) },
            printGetOneMoreCardForDealer = { OutputView.printGetOneMoreCardForDealer() },
        )
        // 한장만 더 안 받기
        blackjackGamer.participants.first().getCurrentCards().trumpCard.count() shouldBe expected
    }
})
