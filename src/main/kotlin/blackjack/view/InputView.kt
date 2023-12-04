package blackjack.view

import blackjack.domain.model.DrawState
import blackjack.domain.model.player.Gambler
import blackjack.domain.model.Name
import blackjack.domain.model.Names

object InputView {
    private const val INPUT_NAMES_DESCRIPTION = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INPUT_DRAW_CARD_DESCRIPTION = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val DRAW_HIT_DEALER_DESCRIPTION = "딜러는 %d이하라 한장의 카드를 더 받았습니다."
    private const val WRONG_INPUT_DRAW_CARD_DESCRIPTION = "잘못된 답변 입니다."
    private const val DRAW_HIT = "y"
    private const val DRAW_STAY = "n"

    private const val NAME_DELIMITER = ","

    fun drawInputNamesView(): Names {
        println(INPUT_NAMES_DESCRIPTION)
        val textNames = readln()
        println()

        return Names.from(textNames.split(NAME_DELIMITER).map { name -> Name.from(name) })
    }

    fun drawHitAndStay(gambler: Gambler): DrawState {
        println(INPUT_DRAW_CARD_DESCRIPTION.format(gambler.name))

        val drawChoice = readln()

        if (drawChoice.isBlank() || (drawChoice != DRAW_HIT && drawChoice != DRAW_STAY)) {
            println(WRONG_INPUT_DRAW_CARD_DESCRIPTION)
            return drawHitAndStay(gambler)
        }

        return if (drawChoice == DRAW_HIT) DrawState.HIT else DrawState.STAY
    }

    fun drawHitDealer(maxHitNumber: Int) {
        println()
        println(DRAW_HIT_DEALER_DESCRIPTION.format(maxHitNumber))
    }
}
