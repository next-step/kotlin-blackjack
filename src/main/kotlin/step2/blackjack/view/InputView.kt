package step2.blackjack.view

import step2.blackjack.model.Gambler
import step2.blackjack.model.Name
import step2.blackjack.model.Names

object InputView {
    private const val INPUT_NAMES_DESCRIPTION = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INPUT_DRAW_CARD_DESCRIPTION = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    private const val NAME_DELIMITER = ","

    fun drawInputNamesView(): Names {
        println(INPUT_NAMES_DESCRIPTION)
        val textNames = readln()
        println()

        require(textNames.isNotBlank()) {
            "게임 참가자들의 이름이 비어 있을 수 없습니다."
        }

        return Names.from(textNames.split(NAME_DELIMITER).map { name -> Name.from(name) })
    }

    fun drawInputDrawCard(gambler: Gambler): String {
        println(INPUT_DRAW_CARD_DESCRIPTION.format(gambler.name))
        return readln()
    }
}
