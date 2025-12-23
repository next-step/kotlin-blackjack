package presentation

import domain.participant.Player

class InputView {
    companion object {
        fun inputPlayers(): List<String> {
            println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
            return readln()
                .split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
        }

        fun inputBetMoney(playerName: String): Int {
            println("${playerName}의 배팅 금액은?")
            return readln().toInt()
        }

//        fun inputBetAmount(player: Player): Int {
//            println("${player.name}의 베팅 금액은?");
//            val input = readln().trim()
//            return input.toDoubleOrNull() ?: run {
//                println("잘못된 입력입니다. 숫자 형식으로 다시 입력해주세요.")
//                inputBetAmount(player)
//            }
//        }

        fun inputAdditionalCard(player: Player): Boolean {
            println("${player.name}는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
            val input = readln().trim().uppercase()
            return when (input) {
                "Y" -> true
                "N" -> false
                else -> {
                    println("잘못된 입력입니다. Y 또는 N을 입력해주세요.")
                    inputAdditionalCard(player)
                }
            }
        }
    }
}
