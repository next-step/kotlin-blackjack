package camp.nextstep.edu.step.step2.view

import camp.nextstep.edu.step.step2.dto.BlackJackResultDto
import camp.nextstep.edu.step.step2.dto.CardDistributionDto
import camp.nextstep.edu.step.step2.dto.PlayerCardDto

object OutputView {

    fun disPlayPlayerCard(playerCardDto: PlayerCardDto) {
        println("${playerCardDto.name}카드 : ${playerCardDto.cards}")
    }

    fun displayInitialPlayer(cardDistributionDto: CardDistributionDto) {
        println("${cardDistributionDto.playerNames.joinToString()}에게 ${cardDistributionDto.initialCardCount}장의 카드를 나누었습니다.")
    }

    fun displayBlackJackResult(blackJackResultDto: BlackJackResultDto) {
        println("${blackJackResultDto.playerName}카드 : ${blackJackResultDto.playerCards} => 결과 : ${blackJackResultDto.playerScore}")
    }

}
