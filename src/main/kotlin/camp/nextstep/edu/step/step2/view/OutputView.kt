package camp.nextstep.edu.step.step2.view

import camp.nextstep.edu.step.step2.dto.BlackJackResultDto
import camp.nextstep.edu.step.step2.dto.CardDistributionDto
import camp.nextstep.edu.step.step2.dto.PlayerCardDto

object OutputView {

    /**
     * @description : 플레이어에게 나눠준 카드를 보여준다.
     */
    fun disPlayPlayerCard(playerCardDto: PlayerCardDto) {
        println("${playerCardDto.name}카드 : ${playerCardDto.cards}")
    }

    /**
     * @description : x, y에게 i개의 카드를 나누었습니다.
     */
    fun displayInitialPlayer(cardDistributionDto: CardDistributionDto) {
        println("${cardDistributionDto.playerNames.joinToString()}에게 ${cardDistributionDto.initialCardCount}장의 카드를 나누었습니다.")
    }

    /**
     * @description : 블랙잭의 결과를 출력한다.
     */
    fun displayBlackJackResult(blackJackResultDto: BlackJackResultDto) {
        println("${blackJackResultDto.playerName}카드 : ${blackJackResultDto.playerCards} => 결과 : ${blackJackResultDto.playerScore}")
    }

}
