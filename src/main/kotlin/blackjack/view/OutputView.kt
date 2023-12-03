package blackjack.view

import blackjack.domain.Cards
import blackjack.domain.Deck
import blackjack.domain.GameResult
import blackjack.domain.Participants
import blackjack.domain.PlayerGameResult
import blackjack.domain.toScore
import java.util.EnumMap

object OutputView {
    fun printDealingHeader(playerNames: String) {
        println("\n딜러와 ${playerNames}에게 ${Deck.INITIAL_DEAL_SIZE}장의 나누었습니다.")
    }

    fun printParticipantCards(participants: Participants) {
        participants.dealer.let {
            printDealerCard(it.name, it.cards)
        }
        participants.players.forEach {
            printPlayerCards(it.name, it.cards)
        }
        println()
    }

    private fun printDealerCard(name: String, cards: Cards) {
        println("${name}카드: ${cards.values.first()}")
    }

    fun printPlayerCards(name: String, cards: Cards) {
        println("${name}카드: $cards")
    }

    fun printGameScore(participants: Participants) {
        participants.dealer.let {
            print("\n${it.name} 카드: ${it.cards} - 결과: ${it.cards.toScore()}")
        }
        participants.players.forEach {
            print("\n${it.name}카드: ${it.cards} - 결과: ${it.cards.toScore()}")
        }
        println("\n")
    }

    fun printPlayerGameResult(playerGameResult: List<PlayerGameResult>) {
        playerGameResult.forEach {
            println("${it.name}: ${it.gameResult.message}")
        }
    }

    fun printDealerGameResult(dealerGameResult: EnumMap<GameResult, Int>) {
        print("딜러: ")
        dealerGameResult.entries.forEach {
            print("${it.value}${it.key.message} ")
        }
        println()
    }

    fun printDealerReceiveMessage() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }
}
