package game.blackjack.v1.ui

import game.blackjack.v1.domain.Dealer
import game.blackjack.v1.domain.Participant
import game.blackjack.v1.domain.Participants

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
    private const val DEALER_DRAW_ADDITIONAL_CARD_PROMPT = "딜러는 16이하라 한장의 카드를 더 받았습니다."

    fun printInitialCardsDraw(dealer: Dealer, participants: Participants) {
        println(String.format(DRAW_INITIAL_CARDS_PROMPT, participants.toPlayerNames()))
        println(dealer)
        println(participants)
    }

    fun printFinalResults(dealer: Dealer, participants: Participants) {
        println(dealer.toFinalResult())
        println(participants.toPlayerFinalResults())
    }

    fun printDealerDrawAdditionalCard() {
        println(DEALER_DRAW_ADDITIONAL_CARD_PROMPT)
    }
}
