package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.Card
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.CardNumber
import blackjack_dealer.entity.CardShape
import blackjack_dealer.entity.Participants
import blackjack_dealer.entity.toGamerCards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackTest : StringSpec({
    val cardDeque = CardDeque.create()
    val cards = listOf(cardDeque.cardDeque.removeLast(), cardDeque.cardDeque.removeLast()).toGamerCards()
    "블랙잭을 수행하여 한장 더 받기를 선택시에 카드의 숫자가 한장 증가한다" {
        // 카드덱에서 j 하트, j 클로버 제거
        cardDeque.cardDeque.removeIf { it == Card(CardNumber.J, CardShape.HEART) }
        cardDeque.cardDeque.removeIf { it == Card(CardNumber.J, CardShape.CLOVER) }

        val customCards =
            listOf(Card(CardNumber.J, CardShape.HEART), Card(CardNumber.J, CardShape.CLOVER)).toGamerCards()
        val dealerCards = listOf(cardDeque.cardDeque.removeLast(), cardDeque.cardDeque.removeLast()).toGamerCards()

        val participant = Participant.newInstance("pita", customCards)
        val dealer = Dealer.newInstance(dealerCards)
        val expected = 3
        val blackJack = BlackJack(cardDeque, dealer, Participants(listOf(participant)))
        // 블랙잭 수행
        blackJack.doGame {
            true
        }
        // 한장만 더 받기
        participant.getCurrentCards().count() shouldBe expected
    }

    "블랙잭을 수행하여 한장 더 안받기 선택시에 카드의 숫자가 개수가 동일하다" {
        val participant = Participant.newInstance("pita", cards)
        val dealerCards = listOf(cardDeque.cardDeque.removeLast(), cardDeque.cardDeque.removeLast()).toGamerCards()
        val dealer = Dealer.newInstance(dealerCards)
        val expected = 2
        val blackJack = BlackJack(cardDeque, dealer, Participants(listOf(participant)))
        // 블랙잭 수행
        blackJack.doGame {
            false
        }
        // 한장만 더 안 받기
        participant.getCurrentCards().count() shouldBe expected
    }
})
