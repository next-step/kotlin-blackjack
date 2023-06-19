package blackjack.domain.view

import blackjack.domain.view.model.input.HitCommand
import blackjack.domain.view.model.input.PlayerBetInput
import blackjack.domain.view.model.input.PlayerNamesInput
import blackjack.domain.view.model.input.PlayersBetInput

object InputView {

    private const val ENTER_PLAYERS = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)"
    private const val ENTER_HIT_OR_NOT = "\n%s는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
    private const val ENTER_BETTING_AMOUNT = "\n%s의 배팅 금액은?"

    fun readPlayers(): PlayersBetInput {
        println(message = ENTER_PLAYERS)

        val playerNames = PlayerNamesInput(text = readln()).playerNames

        return playerNames.map {
            println(message = ENTER_BETTING_AMOUNT.format(it))

            PlayerBetInput(
                name = it,
                bettingAmount = readln().toDouble(),
            )
        }.run(::PlayersBetInput)
    }

    fun readHitOrNot(playerName: String): Boolean {
        println(message = ENTER_HIT_OR_NOT.format(playerName))

        val inputCommand = readln().uppercase()

        return HitCommand.valueOf(value = inputCommand) == HitCommand.Y
    }
}
