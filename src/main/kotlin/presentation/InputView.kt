package presentation

import domain.Money
import domain.Player

class InputView {
    companion object {
        /**
         * 플레이어를 입력받으며, 정확하게 입력되지 않는 경우 반복됩니다.
         */
        fun readPlayerNames(): List<String> {
            while (true) {
                println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
                val names = readln()
                    .split(",")
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }

                if (names.isEmpty()) {
                    println("오류: 한 명 이상의 유효한 플레이어 이름을 쉼표로 구분하여 다시 입력해 주세요.\n")
                    continue
                }

                val duplicates = names.groupingBy { it }
                    .eachCount()
                    .filterValues { it > 1 }.keys

                if (duplicates.isNotEmpty()) {
                    println("오류: 중복된 이름(${duplicates.joinToString(", ")})이 있습니다. 각 플레이어의 이름은 고유해야 합니다.\n")
                    continue
                }

                return names;
            }
        }

        fun inputAdditionalCard(player: Player): Boolean {
            while (true) {
                println("${player.name}는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
                return when (readln().trim().uppercase()) {
                    "Y" -> true
                    "N" -> false
                    else -> {
                        println("잘못된 입력입니다. Y 또는 N을 입력해주세요.")
                        continue
                    }
                }
            }
        }

        /*
            플레이어의 배팅금액을 입력받으며, 정확하게 입력되지 않은 경우 반복됩니다.
         */
        fun readBettingAmount(player: Player, minBet: Int): Money {
            while (true) {
                println("${player.name}의 베팅 금액은? (최소 배팅 금액: $minBet)")
                val bettingAmount = readln().trim().toIntOrNull()

                if (bettingAmount != null && bettingAmount >= minBet) return Money.of(bettingAmount)

                println("잘못된 입력입니다. 최소 배팅 금액 이상을 숫자로 입력해주세요.")
            }
        }
    }
}
