package blackjack_dealer.ui

import blackjack_dealer.domain.Dealer
import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.AllParticipantWithBetAmount
import blackjack_dealer.entity.ParticipantNamesWithBetAmount
import blackjack_dealer.entity.Participants
import blackjack_dealer.entity.result.DealerResult
import blackjack_dealer.entity.result.ParticipantResult
import blackjack_dealer.entity.toAllParticipantWithBetAmount
import blackjack_dealer.ui.printer.GamerPrinter
import blackjack_dealer.ui.printer.board.DealerResultBoardPrinter
import blackjack_dealer.ui.printer.board.ParticipantResultBoardPrinter
import blackjack_dealer.ui.printer.result.GamerResultPrinter

object OutputView {
    fun enterParticipantsName() {
        println(ENTER_PARTICIPANTS_NAME)
    }

    fun enterBetAmountEachParticipant(participants: String, inputBet: () -> Int): AllParticipantWithBetAmount {
        val participantsName = participants.split(',')
        return participantsName.map { name ->
            println(ENTER_BET_AMOUNT.format(name))
            val inputAmount = inputBet()
            ParticipantNamesWithBetAmount(name = name, betAmount = inputAmount)
        }.toAllParticipantWithBetAmount()
    }

    fun printDivideCardsToGamer(dealer: Dealer, participants: Participants) {
        val participantsName = participants.joinToString { it.getGamerName() }
        println(DIVIDE_CARDS_TO_GAMERS.format(dealer.getGamerName(), participantsName))
    }

    fun printGamersInformation(dealer: Dealer, participants: Participants) {
        println(GamerPrinter.print(dealer))
        participants.forEach { participant ->
            printParticipantInformation(participant)
        }
        println()
    }

    fun printParticipantInformation(participant: Participant) {
        println(
            GamerPrinter.print(participant)
        )
    }

    fun askGetOneMoreCard(participant: Participant) {
        println(ASK_GET_MORE_ONE_CARD.format(participant.getGamerName()))
    }

    fun printResult(dealer: Dealer, participants: Participants) {
        println()
        println(GamerResultPrinter.print(dealer))
        participants.forEach { participant ->
            println(GamerResultPrinter.print(participant))
        }
        println()
    }

    fun printGetOneMoreCardForDealer() {
        println(GET_ONE_MORE_CARD_FOR_DEALER)
    }

    fun printFinalResultBoard() {
        println(FINAL_RESULT_BOARD)
    }

    fun printFinalDealerResult(dealerResult: DealerResult) {
        println(
            DealerResultBoardPrinter.print(dealerResult)
        )
    }

    fun printFinalParticipantsResult(participantsResult: List<ParticipantResult>) {
        participantsResult.forEach { participantResult ->
            println(
                ParticipantResultBoardPrinter.print(participantResult)
            )
        }
    }

    private const val ENTER_PARTICIPANTS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val ENTER_BET_AMOUNT = "%s의 베팅 금액은?"
    private const val DIVIDE_CARDS_TO_GAMERS = "%s와 %s에게 2장의 카드를 나누었습니다."
    private const val ASK_GET_MORE_ONE_CARD = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val GET_ONE_MORE_CARD_FOR_DEALER = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val FINAL_RESULT_BOARD = "## 최종 수익"
}
