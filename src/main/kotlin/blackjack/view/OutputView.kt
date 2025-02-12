package blackjack.view

import blackjack.domain.BlackjackGame
import blackjack.domain.BlackjackResults
import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Participants

class OutputView {
    fun showReady(blackjackGame: BlackjackGame) {
        println("\n${blackjackGame.dealer.name}와 ${blackjackGame.participants.joinToString { it.name }}에게 2장의 나누었습니다.")

        showDealerCard(blackjackGame.dealer)
        blackjackGame.participants.forEach {
            showParticipantCardList(it)
        }
        println()
    }

    private fun showDealerCard(dealer: Dealer) {
        println("딜러: ${dealer.cardList.joinToString { card -> createCardName(card) }}")
    }

    fun showParticipantCardList(it: Participant) {
        println("${it.name}카드: ${it.cardList.joinToString { card -> createCardName(card) }}")
    }

    fun showParticipantsInfo(participantList: List<Participant>) {
        participantList.forEach {
            println("${it.name}카드: ${it.cardList.joinToString { card -> createCardName(card) }} - 결과: ${it.score}")
        }
    }

    fun showDealerReceivedCard() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun showDealerInfo(dealer: Dealer) {
        println("\n${dealer.name}카드: ${dealer.cardList.joinToString { card -> createCardName(card) }} - 결과: ${dealer.score}")
    }

    fun showResult(blackjackResults: BlackjackResults) {
        println("\n## 최종 수익")
        blackjackResults.result.forEach { player ->
            println("${player.name}: ${player.money}")
        }
    }

    private fun createCardName(card: Card): String {
        return "${card.rank.nickname}${card.suit.korName}"
    }
}
