package service

import domain.card.CardDeck
import domain.participant.Dealer
import domain.participant.Participant
import domain.participant.Player
import presentation.InputView

class CardDistributorService(private val cardDeck: CardDeck) {
    companion object {
        const val INITIAL_CARD_COUNT = 2
        const val DEALER_ADDITIONAL_CARD_THRESHOLD = 16
    }

    fun distributeInitialCards(participants: List<Participant>) {
        participants.forEach { participant ->
            repeat(INITIAL_CARD_COUNT) {
                val card = cardDeck.drawCard()
                participant.receiveCard(card)
            }
        }
    }

    fun distributeAdditionalCardsForPlayer(player: Player) {
        // 1. 플레이어의 카드 점수를 계산
        // 2. 카드 점수가 21을 넘으면 return;
        // 3. 카드 점수가 21 이하이면 추가 카드 발급 여부를 묻고, Y인 경우 카드를 한 장 더 발급
        while (true) {
            val inputAdditionalCard = InputView.inputAdditionalCard(player)
            if (!inputAdditionalCard) {
                break
            }
            val card = cardDeck.drawCard()
            player.receiveCard(card)

            println("${player.name}카드: ${player.participantHand}")
        }
    }

    fun distributeAdditionalCardsForDealer(dealer: Dealer) {
        // 1. 딜러의 카드 점수를 계산
        // 2. 카드 점수가 17 미만이면 카드를 한 장 더 발급
        // 3. 카드 점수가 17 이상이면 종료
        val dealerScore = dealer.participantHand.calculateScore()
        if (dealerScore > DEALER_ADDITIONAL_CARD_THRESHOLD) {
            return
        }

        val card = cardDeck.drawCard()
        dealer.receiveCard(card)

        println("딜러는 ${DEALER_ADDITIONAL_CARD_THRESHOLD}이하이므로 카드를 한 장 더 받았습니다.")
    }
}
