package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Dealer.Companion.SCORE_SIXTEEN
import blackjack.domain.Participant
import blackjack.domain.asText
import java.util.*

object InputView {
    private const val PLAYER_DELIMITER = ","
    private const val MINIMUM_PLAYER_COUNT = 2
    private const val ILLEGAL_ANSWER_MSG = "y, n만 사용하실 수 있습니다."

    enum class Answer {
        Y, N;

        fun isApprove() = this == Y

        companion object {
            fun of(answer: String): Answer {
                return Answer.values().firstOrNull { answer.uppercase(Locale.getDefault()) == it.name }
                    ?: throw java.lang.IllegalArgumentException(ILLEGAL_ANSWER_MSG)
            }
        }
    }

    fun getPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val players = readln().split(PLAYER_DELIMITER)

        require(players.size >= MINIMUM_PLAYER_COUNT) {
            "반드시 $MINIMUM_PLAYER_COUNT 이상의 참가자가 필요합니다."
        }

        return players
    }

    fun distributeInit(participant: Participant, cardCount: Int, deckFunc: () -> Card) {
        participant.addCard(
            List(cardCount) { deckFunc() }
        )
    }

    fun printCurrentCard(participant: Participant) {
        println("${participant.name}카드: ${participant.cards.asText()}")
    }

    fun distributeToDealer(participant: Participant, deckFunc: () -> Card) {
        if (participant.canReceive()) {
            println("딜러는 ${SCORE_SIXTEEN}이하라 한장의 카드를 더 받았습니다.")
            participant.addCard(deckFunc())
        }
    }

    fun distributeToPlayer(participant: Participant, deckFunc: () -> Card) {
        val name = participant.name
        do {
            println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            val answer = Answer.of(readln())

            if (answer.isApprove()) {
                deckFunc().apply {
                    participant.addCard(this)
                    println("${name}카드 : ${this.fullName}, Score : ${participant.score()}")
                }
            }
        } while (answer.isApprove() && participant.canReceive())
    }
}
