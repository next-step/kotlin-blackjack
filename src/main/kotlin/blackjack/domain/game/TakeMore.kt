package blackjack.domain.game

import blackjack.domain.player.Player

class TakeMore : TakeMoreStrategy {
    override fun wantToTake(player: Player): Boolean {
        println("${player.name}은(는) 한장의 카드를 더 받겠습니까? (예는 Y, 아니오는 N)")

        val text = readln()
        validateYerOrNo(text)

        return judgeYesOrNo(text)
    }

    private fun validateYerOrNo(toCheck: String) {
        if (!(toCheck == "Y" || toCheck == "y" || toCheck == "N" || toCheck == "n")) {
            throw IllegalArgumentException("입력은 Y or N로 해야 합니다.")
        }
    }

    private fun judgeYesOrNo(toCheck: String): Boolean {
        return toCheck == "Y" || toCheck == "y"
    }
}
