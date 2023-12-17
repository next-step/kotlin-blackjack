package game.blackjack.ui

import game.blackjack.domain.Dealer
import game.blackjack.domain.Participant
import game.blackjack.domain.Participants

object Input {
    private const val PARTICIPANTS_PROMPT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val DRAW_ADDITIONAL_CARD_PROMPT = "%s은(는) 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    fun getParticipantNames(): List<String> {
        println(PARTICIPANTS_PROMPT)
        return readln().split(",")
    }

    fun isDrawAdditionalCard(participant: Participant): Boolean {
        println(String.format(DRAW_ADDITIONAL_CARD_PROMPT, participant.name))
        return readln().trim().lowercase() == "y"
    }
}

object Output {
    private const val DRAW_INITIAL_CARDS_PROMPT = "딜러와 %s에게 각각 2장을 나누었습니다."

    fun printInitialCardsDraw(dealer: Dealer, participants: Participants) {
        println(String.format(DRAW_INITIAL_CARDS_PROMPT, participants.toNames()))
        println(dealer)
        println(participants)
    }

    fun printFinalResults(participants: Participants) {
        println(participants.toFinalResult())
    }
}
