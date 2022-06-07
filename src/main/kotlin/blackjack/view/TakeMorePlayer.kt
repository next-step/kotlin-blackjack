package blackjack.view

import blackjack.domain.game.TakeMorePlayerStrategy
import blackjack.domain.player.Player

class TakeMorePlayer : TakeMorePlayerStrategy {
    override fun wantToTake(player: Player): Boolean {
        println("${player.name}은(는) 한장의 카드를 더 받겠습니까? (예는 Y, 아니오는 N)")

        val text = readln()
        validateYerOrNo(text)

        return judgeYesOrNo(text)
    }

    private fun validateYerOrNo(toCheck: String) {
        require(YES_NO_STRING.contains(toCheck.uppercase())) { "입력은 Y or N으로 해야 합니다." }
    }

    private fun judgeYesOrNo(toCheck: String): Boolean {
        return toCheck.uppercase() == "Y"
    }

    companion object {
        val YES_NO_STRING = listOf("Y", "N")
    }
}
