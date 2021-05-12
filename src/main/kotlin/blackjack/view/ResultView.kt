package blackjack.view

import blackjack.domain.*

object ResultView {
    private const val NAME_SEPARATOR = ","

    private val Card.fullName: String
        get() = CardDisplayNumber.retrieveDisplayName(number) + suite.koreanName

    fun printAllPlayerCards(game: Game) {
        game.participants.forEach {
            printPlayerCards(it.name, it.cards)
        }
    }

    fun printAllResult(game: Game) {
        game.participants.forEach {
            printResult(it.name, it.cards)
        }
    }

    fun printGameResultTitle() {
        println("## 최종 승패")
    }

    fun printGameResults(dealer: Dealer, game: Game) {
        printParticipantGameResult(dealer)

        printAllParticipantGameResult(game)
    }

    private fun printAllParticipantGameResult(game: Game) {
        game.participants.forEach { printParticipantGameResult(it) }
    }

    private fun printParticipantGameResult(participant: Participant) {
        println("${participant.name} : ${participant.result}")
    }

    fun printInitNotice(names: List<String>, blackJackCardCount: Int) {
        println("${names.joinToString(NAME_SEPARATOR)} 에게 $blackJackCardCount 장을 나누었습니다. ")
    }

    fun printPlayerCards(name: String, cards: PlayerCards) {
        println("${name}카드: ${explainCards(cards)}")
    }

    private fun printResult(name: String, cards: PlayerCards) {
        println("${name}카드: ${explainCards(cards)} - 결과 : ${cards.score}")
    }

    private fun explainCards(cards: PlayerCards): String {
        return cards.joinToString(NAME_SEPARATOR) { it.fullName }
    }
}
