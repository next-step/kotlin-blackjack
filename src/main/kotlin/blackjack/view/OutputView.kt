package blackjack.view

import blackjack.domain.*

object OutputView {
    fun printPlayerStates(participants: List<Participant>, initialDealSize: Int) {
        val names = participants.joinToString(", ") { it.name }
        println("\n${names}에게 ${initialDealSize}장의 나누었습니다.")
        participants.forEach {
            printParticipantCards(it)
        }
        println()
    }

    fun printParticipantCards(participant: Participant) {
        println("${participant.name}카드: ${participant.cards}")
    }

    fun printGameScore(participants: List<Participant>) {
        participants.forEach {
            print("\n${it.name}카드: ${it.cards} - 결과: ${it.cards.toScore()}")
        }
    }

    fun printPlayerGameResult(players: Players, gameResults: List<GameResult>) {
        gameResults.forEachIndexed { index, gameResult ->
            println("${players[index].name}: ${gameResult.message}")
        }
    }

    fun printDealerGameResult(gameResults: List<GameResult>) {
        println()
        val result = gameResults.groupingBy { it }.eachCount().toList()
            .joinToString(" ") { "${it.second}${it.first.message}" }
        println("\n딜러: $result")
    }

}
