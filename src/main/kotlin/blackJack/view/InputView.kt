package blackJack.view

import blackJack.domain.Player

object InputView {
    fun inputPlayer(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)")
        return checkNullOrBlank(readLine()).split(",").map { it.trim() }
    }

    fun inputWhether(player: Player): String {
        println("${player.name}님은 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
        return checkWhether(checkNullOrBlank(readLine()))
    }

    private fun checkNullOrBlank(names: String?): String {
        if (!names.isNullOrBlank()) {
            return names
        }
        throw IllegalArgumentException("공백값과 null값은 입력하지 말아주세요")
    }

    private fun checkWhether(whether: String): String {
        if (whether == "y" || whether == "n") {
            return whether
        }
        throw IllegalArgumentException("y또는 n으로 답해주세요")
    }
}
