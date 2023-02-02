package blackjack.dto

interface Output {
    fun printGameStartMessage(names: List<String>)

    fun printParticipantCards(participant: ParticipantDto)

    fun printResultWithScore(participants: ParticipantsDto)

    fun printResult(results: ResultsDto)

    fun printPlayerBust(name: String)

    fun printPlayerBlackjack(name: String)

    fun printDealerDrawMessage()

    fun printParticipantsCards(participants: ParticipantsDto)
}
